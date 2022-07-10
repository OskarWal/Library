package com.example.demo.controller;

import com.example.demo.entity.Authority;
import com.example.demo.entity.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegistrationController
{
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService)
    {
        this.userService = userService;
    }


    @GetMapping(value = {"/register"})
    public String registerForm(@RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages,Model model)
    {
        model.addAttribute("user", new User());
        model.addAttribute("badMessages",badMessages);
        model.addAttribute("goodMessages",goodMessages);

        return "register";
    }


    @GetMapping(value = {"/registerAdmin"})
    public String registerAdmin(@RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages,Model model)
    {
        model.addAttribute("user", new User());

        model.addAttribute("badMessages",badMessages);
        model.addAttribute("goodMessages",goodMessages);

        return "registerAdmin";
    }

    @PostMapping("/registerAdmin")
    public String saveAdmin(@ModelAttribute("user") User user, @RequestParam("superPassword") String superPassword, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages,Model model)
    {
        String validator = validateSuper(user,superPassword);
        if (!validator.isEmpty())
        {
            if(badMessages == null)
                badMessages = new ArrayList<>();

            badMessages.add(validator);
            model.addAttribute("badMessages",badMessages);
            model.addAttribute("goodMessages",goodMessages);
            return "registerAdmin";
        }



        user.getAuthorities().add(new Authority(user, "ROLE_ADMIN"));
        userService.saveUser(user);

        if(goodMessages == null)
            goodMessages = new ArrayList<>();
        goodMessages.add("Utworzono konto administratora");

        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);

        return "redirect:/registerAdmin";
    }


    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages,Model model)
    {
        String validator = validate(user);
        if (!validator.isEmpty())
        {
            if(badMessages == null)
                badMessages = new ArrayList<>();

            badMessages.add(validator);
            model.addAttribute("badMessages",badMessages);
            model.addAttribute("goodMessages",goodMessages);
            return "register";
        }
        user.getAuthorities().add(new Authority(user, "ROLE_USER"));
        userService.saveUser(user);

        if(goodMessages == null)
            goodMessages = new ArrayList<>();
        goodMessages.add("Utworzono konto. Możesz się zalogować");
        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);

        return "redirect:/";
    }

    private String validate(User user)
    {
        if (userService.getUser(user.getUsername()) != null)
        {
            return "Użytkownik o takiej nazwie już istnieje";
        } else if (user.getUsername().isEmpty() || user.getPassword().isEmpty())
        {
            return "Wypełnij wszystkie pola";
        }
        return "";
    }

    private String validateSuper(User user, String superPassword)
    {
        if(superPassword.compareTo("admin")!=0)
            return "Podano niepoprawny kod";

        return validate(user);
    }

    @RequestMapping("/logout")
    public String logout(Model model,@RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {
        SecurityContextHolder.getContext().setAuthentication(null);
        if(goodMessages == null)
            goodMessages = new ArrayList<>();
        goodMessages.add("Wylogowano");
        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);
        return "redirect:/";
    }

}
