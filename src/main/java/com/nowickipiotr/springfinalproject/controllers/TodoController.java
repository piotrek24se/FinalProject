package com.nowickipiotr.springfinalproject.controllers;

import com.nowickipiotr.springfinalproject.models.Entry;
import com.nowickipiotr.springfinalproject.repositories.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TodoController {

    @Autowired
    EntryRepository entryRepository;

    @GetMapping("/wall")
    String wall(Model model) {

        List<Entry> entryList = entryRepository.findAll();

        if (entryList.size() == 0) {
            return "emptyWall";
        } else {
            model.addAttribute("entryList", entryList);
            return "wall";
        }


    }



}
