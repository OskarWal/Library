package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;



    @GetMapping("/formadd")
    public String addForm(Model model)
    {
        Book book = new Book();
        List<Category> categories = categoryService.getCategories();
        List<Author> authors = authorService.getAuthors();
        model.addAttribute("book",book);
        model.addAttribute("categories", categories);
        model.addAttribute("authors", authors);
        return "addbookform";
    }

    @GetMapping("/edit")
    public String editBook(Model model, @RequestParam("bookId") int bookId, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {
        Book book = bookService.getBookById(bookId);

        if(book == null)
        {
            if(badMessages == null)
                badMessages = new ArrayList<>();
            badMessages.add("Błąd. Podana książka nie istnieje");
            model.addAttribute("goodMessages",goodMessages);
            model.addAttribute("badMessages",badMessages);
            return "redirect:/editList";
        }


        List<Category> categories = categoryService.getCategories();
        List<Author> authors = authorService.getAuthors();
        List<Integer> book_authors = new ArrayList<>();
        for(Author b: book.getAuthors())
        {
            book_authors.add(b.getId());
        }
        model.addAttribute("book",book);
        model.addAttribute("categories", categories);
        model.addAttribute("authors", authors);
        model.addAttribute("book_authors", book_authors);
        return "editBookForm";
    }


    @PostMapping("/delete")
    public String deleteBook(Model model, @RequestParam("bookId") int bookId,@RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {
        Book book = bookService.getBookById(bookId);

        if(book == null)
        {
            if(badMessages == null)
                badMessages = new ArrayList<>();
            badMessages.add("Błąd. Podana książka nie istnieje");
            model.addAttribute("goodMessages",goodMessages);
            model.addAttribute("badMessages",badMessages);
            return "redirect:/editList";
        }


        bookService.deleteBook(book);

        if(goodMessages == null)
            goodMessages = new ArrayList<>();
        goodMessages.add("Usunieto książkę");
        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);

        return "redirect:/editList";
    }

    @PostMapping("/saveBook")
    public String saveBook(Model model, @ModelAttribute("book") Book book, @RequestParam("authors_id") List<Integer> id, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {

        bookService.saveBook(book);


        for(int author_id: id)
        {
            book.addAuthor(authorService.getAuthor(author_id));
        }

        bookService.saveBook(book);

        if(goodMessages == null)
            goodMessages = new ArrayList<>();
        goodMessages.add("Dodano książkę");
        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);

        return "redirect:/";
    }

    @PostMapping("/updateBook")
    public String updateBook(Model model, @ModelAttribute("book") Book book, @RequestParam("bookId") int bookId, @RequestParam("authors_id") List<Integer> id, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {

        Book old = bookService.getBookById(bookId);

        if(old == null)
        {
            if(badMessages == null)
                badMessages = new ArrayList<>();
            badMessages.add("Błąd. Podana książka nie istnieje");
            model.addAttribute("goodMessages",goodMessages);
            model.addAttribute("badMessages",badMessages);
            return "redirect:/editList";
        }

        old.setTitle(book.getTitle());
        old.setPrice(book.getPrice());
        old.setPublisher(book.getPublisher());
        old.setCategory(book.getCategory());
        old.setImage_url(book.getImage_url());
        old.setDescription(book.getDescription());
        old.getAuthors().clear();


        for(int author_id: id)
        {
            old.addAuthor(authorService.getAuthor(author_id));
        }

        bookService.saveBook(old);

        if(goodMessages == null)
            goodMessages = new ArrayList<>();
        goodMessages.add("Edytowano książkę");
        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);

        return "redirect:/editList";
    }

    @GetMapping("/details")
    public String booksDetails(Model model, @RequestParam("bookId") int bookId)
    {
        Book book = bookService.getBookById(bookId);

        if(book == null)
        {
            List<String> badMessages = new ArrayList<>();
            badMessages.add("Niepoprawne ID");
            model.addAttribute("badMessages", badMessages);
            return "redirect:/";
        }


        model.addAttribute("book", book);

        return "BookDetails";
    }


}
