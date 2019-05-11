package com.nowickipiotr.springfinalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/private")
    String priv() {
        return "private";
    }

    @GetMapping("/test")
    String test() {
        return "test";
    }



}
