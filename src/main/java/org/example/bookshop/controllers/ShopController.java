package org.example.bookshop.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShopController {
    @GetMapping(value = {"/"})
    public String root(Model model) {
        return "redirect:/index.html";
    }

    @GetMapping(value = {"/{name}.html"})
    public String htmlMapping(@PathVariable String name, HttpSession session) {
        return name;
    }
}