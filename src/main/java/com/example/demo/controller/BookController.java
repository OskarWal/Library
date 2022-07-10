package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;


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
        Ksiazka book = new Ksiazka();
        List<Kategoria> categories = categoryService.getCategories();
        List<Autor> authors = authorService.getAuthors();
        model.addAttribute("book",book);
        model.addAttribute("categories", categories);
        model.addAttribute("authors", authors);
        return "addbookform";
    }

    @GetMapping("/edit")
    public String editBook(Model model, @RequestParam("bookId") int bookId, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {
        Ksiazka book = bookService.getBookById(bookId);

        if(book == null)
        {
            if(badMessages == null)
                badMessages = new ArrayList<>();
            badMessages.add("Błąd. Podana książka nie istnieje");
            model.addAttribute("goodMessages",goodMessages);
            model.addAttribute("badMessages",badMessages);
            return "redirect:/editList";
        }


        List<Kategoria> categories = categoryService.getCategories();
        List<Autor> authors = authorService.getAuthors();
        List<Integer> book_authors = new ArrayList<>();
        for(Autor b: book.getAutorzy())
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
        Ksiazka book = bookService.getBookById(bookId);

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
    public String saveBook(Model model,@ModelAttribute("book") Ksiazka ksiazka, @RequestParam("authors_id") List<Integer> id, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {

        bookService.saveBook(ksiazka);


        for(int author_id: id)
        {
            ksiazka.addAutor(authorService.getAuthor(author_id));
        }

        bookService.saveBook(ksiazka);

        if(goodMessages == null)
            goodMessages = new ArrayList<>();
        goodMessages.add("Dodano książkę");
        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);

        return "redirect:/";
    }

    @PostMapping("/updateBook")
    public String updateBook(Model model,@ModelAttribute("book") Ksiazka ksiazka,@RequestParam("bookId") int bookId, @RequestParam("authors_id") List<Integer> id, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {

        Ksiazka old = bookService.getBookById(bookId);

        if(old == null)
        {
            if(badMessages == null)
                badMessages = new ArrayList<>();
            badMessages.add("Błąd. Podana książka nie istnieje");
            model.addAttribute("goodMessages",goodMessages);
            model.addAttribute("badMessages",badMessages);
            return "redirect:/editList";
        }

        old.setNazwa(ksiazka.getNazwa());
        old.setCena(ksiazka.getCena());
        old.setWydawnictwo(ksiazka.getWydawnictwo());
        old.setKategoria(ksiazka.getKategoria());
        old.setImage_url(ksiazka.getImage_url());
        old.setOpis(ksiazka.getOpis());
        old.getAutorzy().clear();


        for(int author_id: id)
        {
            old.addAutor(authorService.getAuthor(author_id));
        }

        bookService.saveBook(old);

        if(goodMessages == null)
            goodMessages = new ArrayList<>();
        goodMessages.add("Edytowano książkę");
        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);

        return "redirect:/";
    }

    @GetMapping("/details")
    public String booksDetails(Model model, @RequestParam("bookId") int bookId)
    {
        Ksiazka book = bookService.getBookById(bookId);

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
