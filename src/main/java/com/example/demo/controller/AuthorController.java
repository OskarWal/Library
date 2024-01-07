package com.example.demo.controller;

import com.example.demo.entity.Author;
import com.example.demo.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController
{
    @Autowired
    private AuthorService authorService;


    @GetMapping("/list")
    public String listAuthors(Model model,@RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {
        List<Author> authors = authorService.getAuthors();
        model.addAttribute("authors", authors);
        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);
        return "authorslist";
    }

    @GetMapping("/formadd")
    public String addForm(Model model)
    {
        Author author = new Author();
        model.addAttribute("author",author);
        return "addauthorform";
    }

    @GetMapping("/formedit")
    public String formedit(Model model, @RequestParam("authorId") int authorId, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {
        Author author = authorService.getAuthor(authorId);

        if(author == null)
        {
            if(badMessages == null)
            {
                badMessages = new ArrayList<>();
                badMessages.add("Błąd podczas edycji: Podano niepoprawne id autora");
            }
            model.addAttribute("goodMessages",goodMessages);
            model.addAttribute("badMessages",badMessages);

            return "redirect:/authors/list";
        }


        model.addAttribute("author",author);
        return "editAuthorForm";
    }

    @PostMapping("/updateAuthor")
    public String updateAuthor(@ModelAttribute("author") Author author, @RequestParam("authorId") int authorId, Model model, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {
        Author old = authorService.getAuthor(authorId);

        if(old == null)
        {
            if(badMessages == null)
            {
                badMessages = new ArrayList<>();
                badMessages.add("Błąd podczas edycji: Podano niepoprawne id autora");
            }
            model.addAttribute("goodMessages",goodMessages);
            model.addAttribute("badMessages",badMessages);

            return "redirect:/authors/list";
        }

        old.setName(author.getName());
        old.setSurname(author.getSurname());
        authorService.saveAuthor(old);

        if(goodMessages == null)
        {
            goodMessages = new ArrayList<>();
            goodMessages.add("Poprawnie edytowano autora");
        }
        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);

        return "redirect:/authors/list";
    }

    @PostMapping("/saveAuthor")
    public String saveAuthor(Model model, @ModelAttribute("author") Author author, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {

        authorService.saveAuthor(author);
        if(goodMessages == null)
        {
            goodMessages = new ArrayList<>();
            goodMessages.add("Poprawnie dodano autora");
        }
        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);

        return "redirect:/authors/list";
    }

    @PostMapping("/deleteAuthor")
    public String deleteAuthor(@RequestParam("authorId")int authorId, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages, Model model)
    {
        Author author = authorService.getAuthor(authorId);
        if(author == null)
        {
            if(badMessages == null)
            {
                badMessages = new ArrayList<>();
                badMessages.add("Błąd podczas usuwania: Podany autor nie istnieje");
            }
            model.addAttribute("goodMessages",goodMessages);
            model.addAttribute("badMessages",badMessages);

            return "redirect:/authors/list";
        }


        authorService.deleteAuthor(author);

        if(goodMessages == null)
        {
            goodMessages = new ArrayList<>();
            goodMessages.add("Usunięto autora");
        }
        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);

        return "redirect:/authors/list";
    }


}
