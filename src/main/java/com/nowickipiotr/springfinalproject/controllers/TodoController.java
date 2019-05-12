package com.nowickipiotr.springfinalproject.controllers;

import com.nowickipiotr.springfinalproject.models.Entry;
import com.nowickipiotr.springfinalproject.models.User;
import com.nowickipiotr.springfinalproject.models.enums.EntryStatus;
import com.nowickipiotr.springfinalproject.models.enums.EntryType;
import com.nowickipiotr.springfinalproject.models.enums.UserStatus;
import com.nowickipiotr.springfinalproject.models.enums.UserType;
import com.nowickipiotr.springfinalproject.repositories.EntryRepository;
import com.nowickipiotr.springfinalproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class TodoController {

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    UserRepository userRepository;

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

    @GetMapping("/addPost")
    String addPost() {
        return "newPost";
    }

    @PostMapping("/newPost")
    String newPost(
            @RequestParam("content") String content,
            @RequestParam("dateOfCreation") String dateOfCreation,
            @RequestParam("entryStatus") String entryStatus,
            @RequestParam("entryType") String entryType,
            @RequestParam("userName") String userName,
            Model model
    ) {

        User user = new User(
                userName,
                "test",
                "test",
                userName,
                "12.05.2019",
                UserStatus.ACTIVE,
                UserType.PRIVATE);

        Entry entry = new Entry();
        entry.setContent(content);
        entry.setDateOfCreation(dateOfCreation);
        entry.setStatus(Enum.valueOf(EntryStatus.class, entryStatus));
        entry.setType(Enum.valueOf(EntryType.class, entryType));

        Set<Entry> entrySet = new HashSet<>();
        entrySet.add(entry);

        user.setEntries(entrySet);

        entry.setUser(user);

        model.addAttribute("entry", entrySet);
        model.addAttribute("user", user);

        userRepository.save(user);
        entryRepository.save(entry);

        List<Entry> entryListAfterAddingPost = entryRepository.findAll();

        model.addAttribute("entryListAfterAddingPost", entryListAfterAddingPost);

        return "updatedWall";
    }





}
