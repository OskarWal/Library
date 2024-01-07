package com.example.demo.controller;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Book;
import com.example.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController
{

    @Autowired
    public UserService userService;
    @Autowired
    public BookService bookService;
    @Autowired
    public OrderItemsService orderItemsService;
    @Autowired
    public OrderService orderService;
    @Autowired
    public CartService cartService;


    @GetMapping("/cart")
    public String listCategory(Model model,@RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Cart> cart_items = cartService.getCart(username);

        model.addAttribute("cart_items",cart_items);
        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);
        return "cart";
    }

    @PostMapping("/deleteCartItem")
    public String deleteCartItem(@RequestParam("bookId") int bookId)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Cart> cartItemsList = cartService.getCart(username);
        for(Cart item:cartItemsList)
        {
            if(item.getBookId().getId() == bookId)
            {
                cartService.deleteCartItem(item);
                break;
            }
        }

        return "redirect:/cart";
    }

    @PostMapping("/addItemToCart")
    public String addItemToCart(@RequestParam("bookId")int bookId, @RequestParam("quantity") int quantity, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages, Model model)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Book book = bookService.getBookById(bookId);
        if(quantity > 0)
        {
            List<Cart> cartList = cartService.getCart(username);
            for (Cart item: cartList)
            {
                if(item.getBookId().getId() == bookId)
                {
                    int old = item.getQuantity();
                    item.setQuantity(old + quantity);

                    cartService.saveCart(item);

                    if(goodMessages == null)
                        goodMessages = new ArrayList<>();
                    goodMessages.add("Ksiązka znajduje się już w koszyku. Zmieniono jej ilość");
                    model.addAttribute("goodMessages",goodMessages);
                    model.addAttribute("badMessages",badMessages);

                    return "redirect:/";
                }
            }

            Cart cart = new Cart(username,book,quantity);
            cartService.saveCart(cart);

            if(goodMessages == null)
                goodMessages = new ArrayList<>();
            goodMessages.add("Dodano książkę do koszyka");
            model.addAttribute("goodMessages",goodMessages);
            model.addAttribute("badMessages",badMessages);

            return "redirect:/";

        }

        if(badMessages == null)
            badMessages = new ArrayList<>();
        badMessages.add("Błąd podczas dodawania do koszyka. Podano nieprawidłową ilość");
        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);

        return "redirect:/";
    }

    @PostMapping("/editCartItem")
    public String editCartItem(@RequestParam("bookId") int bookId, @RequestParam("quantity") int quantity)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Cart> cartItemsList = cartService.getCart(username);
        for(Cart item:cartItemsList)
        {
            if(item.getBookId().getId() == bookId)
            {
                item.setQuantity(quantity);
                cartService.saveCart(item);
                break;
            }
        }

        return "redirect:/cart";
    }


}
