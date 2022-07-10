package com.example.demo.controller;

import com.example.demo.entity.Autor;
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
        List<Autor> authors = authorService.getAuthors();
        model.addAttribute("authors", authors);
        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);
        return "authorslist";
    }

    @GetMapping("/formadd")
    public String addForm(Model model)
    {
        Autor author = new Autor();
        model.addAttribute("author",author);
        return "addauthorform";
    }

    @GetMapping("/formedit")
    public String formedit(Model model, @RequestParam("authorId") int authorId, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {
        Autor author = authorService.getAuthor(authorId);

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
    public String updateAuthor(@ModelAttribute("author")Autor autor, @RequestParam("authorId") int authorId, Model model, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {
        Autor old = authorService.getAuthor(authorId);

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

        old.setImie(autor.getImie());
        old.setNazwisko(autor.getNazwisko());
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
    public String saveAuthor(Model model,@ModelAttribute("author")Autor autor, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {

        authorService.saveAuthor(autor);
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
        Autor autor = authorService.getAuthor(authorId);
        if(autor == null)
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


        authorService.deleteAuthor(autor);

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
