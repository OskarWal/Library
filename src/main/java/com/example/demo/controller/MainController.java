package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.services.BookService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;


@Controller
@RequestMapping("/")
public class MainController
{

    @Autowired
    public BookService bookService;
    @Autowired
    public UserService userService;


    @GetMapping("/")
    public String main(Model model, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {

        List<Book> books = bookService.getBooks();
        model.addAttribute("books",books);
        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);

        return "HomePage";
    }
    @GetMapping("/editList")
    public String editList(Model model, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {

        List<Book> books = bookService.getBooks();
        model.addAttribute("books",books);
        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);


        return "editList";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam(name = "searchParam",required = false) String searchParam,@RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages )
    {
        String search = searchParam.toLowerCase(Locale.ROOT);
        Set<Book> books = new HashSet<>();
        List<Book> booksList = bookService.getBooks();
        for(Book book : booksList)
        {

            if(book.getTitle().toLowerCase(Locale.ROOT).contains(search))
            {
                books.add(book);
                continue;
            }
            if(book.getCategory().getName().toLowerCase(Locale.ROOT).contains(search))
            {
                books.add(book);
                continue;
            }
            if (book.getPublisher().toLowerCase(Locale.ROOT).contains(search))
            {
                books.add(book);
                continue;
            }

            for(Author author :book.getAuthors())
            {
                String bookAuthor = author.getName() + " " + author.getSurname();
                if(bookAuthor.toLowerCase(Locale.ROOT).contains(search))
                {
                    books.add(book);
                    break;
                }
            }


        }
        goodMessages = new ArrayList<>();
        goodMessages.add("Wyniki wyszukiwania dla: " + searchParam);

        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);

        model.addAttribute("books",books);
        return "HomePage";
    }


}
