package com.example.demo.controller;

import com.example.demo.entity.Authority;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItems;
import com.example.demo.services.CartService;
import com.example.demo.services.OrderItemsService;
import com.example.demo.services.OrderService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController
{
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemsService orderItemsService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @GetMapping("/makeOrder")
    public String makeOrder(Model model, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Cart> cartList = cartService.getCart(username);

        if(cartList.size() == 0)
        {
            if(badMessages == null)
            {
                badMessages = new ArrayList<>();
                badMessages.add("Zamówienie nie zostało utorzone. Powód: Posiadasz pusty koszyk");
            }
            model.addAttribute("goodMessages",goodMessages);
            model.addAttribute("badMessages",badMessages);

            return "redirect:/";
        }


        Order order = new Order("Nowe",username);
        orderService.saveOrder(order);

        for(Cart item:cartList)
        {
            orderItemsService.saveOrderItems(new OrderItems(item.getBookId(),order,item.getQuantity()));
            cartService.deleteCartItem(item);
        }
        if(goodMessages == null)
        {
            goodMessages = new ArrayList<>();
        }

        goodMessages.add("Zrobiono zamówienie");

        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);

        return "redirect:/";
    }

    @GetMapping("/myOrders")
    public String myOrders(Model model)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Order> orderList = orderService.getAllUserOrders(username);

        model.addAttribute("orders",orderList);


        return "UserOrderList";
    }

    @GetMapping("/showOrderItems")
    public String showOrderItems(Model model, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages, @RequestParam(name = "orderId",required = true) int orderId)
    {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Authority> user_auth = userService.getUser(username).getAuthorities();
        boolean isAdmin = false;
        for(Authority authority: user_auth)
        {
            if(authority.getAuthority().compareTo("ROLE_ADMIN") == 0)
            {
                isAdmin = true;
                break;
            }
        }

        Order order = orderService.getOrderById(orderId);
        if(order == null || ((order.getUser().compareTo(username) !=0) && !isAdmin))
        {
            if(badMessages == null)
                badMessages = new ArrayList<>();
            badMessages.add("Nie posiadasz uprawnien");

            model.addAttribute("goodMessages",goodMessages);
            model.addAttribute("badMessages",badMessages);
            return "redirect:/";
        }
        List<OrderItems> orderList = orderItemsService.getAllItemsOrder(order);


        model.addAttribute("items",orderList);



        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);

        return "OrderItemsList";
    }

    @GetMapping("/showAll")
    public String showAll(Model model, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {


        List<Order> orders = orderService.getAllOrders();


        model.addAttribute("orders",orders);

        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);

        return "AdminOrderList";
    }

    @PostMapping("/changeStatus")
    public String changeStatus(Model model, @RequestParam(name = "orderId",required = true) int orderId, @RequestParam(name = "status",required = true) String status,@RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {


        Order order = orderService.getOrderById(orderId);
        if(order != null)
        {
            order.setStatus(status);
            orderService.saveOrder(order);
            if(goodMessages == null)
                goodMessages = new ArrayList<>();
            goodMessages.add("Zmieniono status");
        }
        else
        {
            if(badMessages == null)
                badMessages = new ArrayList<>();
            badMessages.add("Brak podanego ID");
        }



        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);

        return "redirect:/order/showAll";
    }

}
