package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String listCategory(Model model,@RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categories",categories);
        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);
        return "CategoriesList";
    }

    @GetMapping("/formadd")
    public String addForm(Model model)
    {
        Category category = new Category();
        model.addAttribute("category",category);
        return "AddCategoryForm";
    }

    @GetMapping("/formedit")
    public String formedit(Model model, @RequestParam("categoryId") int categoryId, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {
        Category category = categoryService.getCategory(categoryId);

        if(category == null)
        {
            if(badMessages == null)
                badMessages = new ArrayList<>();
            badMessages.add("Błąd. Podana kategoria nie istnieje");
            model.addAttribute("goodMessages",goodMessages);
            model.addAttribute("badMessages",badMessages);
            return "redirect:/categories/list";
        }


        model.addAttribute("category",category);
        return "EditCategoryForm";
    }

    @PostMapping("/updateCategory")
    public String updateCategory(@ModelAttribute("category") Category category, @RequestParam("categoryId") int categoryId, Model model, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages)
    {
        Category old = categoryService.getCategory(categoryId);
        if(old == null)
        {
            if(badMessages == null)
                badMessages = new ArrayList<>();
            badMessages.add("Błąd. Podana kategoria nie istnieje");
            model.addAttribute("goodMessages",goodMessages);
            model.addAttribute("badMessages",badMessages);
            return "redirect:/categories/list";
        }


        old.setName(category.getName());
        categoryService.saveCategory(old);

        if(goodMessages == null)
            goodMessages = new ArrayList<>();
        goodMessages.add("Edytowano kategorię");
        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);

        return "redirect:/categories/list";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(@ModelAttribute("category") Category category, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages, Model model)
    {
        categoryService.saveCategory(category);

        if(goodMessages == null)
            goodMessages = new ArrayList<>();
        goodMessages.add("Dodano kategorię");
        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);

        return "redirect:/categories/list";
    }

    @PostMapping("/deleteCategory")
    public String deleteCategory(@RequestParam("categoryId")int categoryId, @RequestParam(name = "goodMessages",required = false) List<String> goodMessages, @RequestParam(name = "badMessages",required = false) List<String> badMessages, Model model)
    {
        Category category = categoryService.getCategory(categoryId);

        if(category == null)
        {
            if(badMessages == null)
                badMessages = new ArrayList<>();
            badMessages.add("Błąd. Podana kategoria nie istnieje");
            model.addAttribute("goodMessages",goodMessages);
            model.addAttribute("badMessages",badMessages);

            return "redirect:/";
        }

        categoryService.deleteCategory(category);
        if(goodMessages == null)
            goodMessages = new ArrayList<>();
        goodMessages.add("Usunięto kategorię");
        model.addAttribute("goodMessages",goodMessages);
        model.addAttribute("badMessages",badMessages);

        return "redirect:/categories/list";
    }

}
