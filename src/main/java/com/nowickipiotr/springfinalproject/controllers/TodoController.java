package com.nowickipiotr.springfinalproject.controllers;

import com.nowickipiotr.springfinalproject.models.Authority;
import com.nowickipiotr.springfinalproject.models.Entry;
import com.nowickipiotr.springfinalproject.models.User;
import com.nowickipiotr.springfinalproject.models.enums.EntryStatus;
import com.nowickipiotr.springfinalproject.models.enums.EntryType;
import com.nowickipiotr.springfinalproject.models.enums.UserStatus;
import com.nowickipiotr.springfinalproject.models.enums.UserType;
import com.nowickipiotr.springfinalproject.repositories.AuthorityRepository;
import com.nowickipiotr.springfinalproject.repositories.EntryRepository;
import com.nowickipiotr.springfinalproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class TodoController {

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    List<Entry> entryList;

    @GetMapping("/wall")
    String wall(Model model) {

        entryList = entryRepository.findAll();

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
            @RequestParam("entryStatus") String entryStatus,
            @RequestParam("entryType") String entryType,
            Model model
    ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentLoggedInUsername = authentication.getName();

        User user = userRepository.findByUserName(currentLoggedInUsername).orElseThrow(EntityNotFoundException::new);

        LocalDateTime now = LocalDateTime.now();

        Entry entry = new Entry();
        entry.setContent(content);
        entry.setDateOfCreation(now.format(formatter));
        entry.setStatus(Enum.valueOf(EntryStatus.class, entryStatus));
        entry.setType(Enum.valueOf(EntryType.class, entryType));

        Set<Entry> entrySet = new HashSet<>();
        entrySet.add(entry);

        user.setEntries(entrySet);

        entry.setUser(user);

        model.addAttribute("entry", entrySet);
        model.addAttribute("user", user);

        entryRepository.save(entry);

        entryList = entryRepository.findAll();

        model.addAttribute("entryList", entryList);

        return "wall";
    }

    @GetMapping("/signUp")
    String signUp() {
        return "/register";
    }

    @PostMapping("/userCreation")
    String userCreation(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("linkAccountName") String linkAccountName,
            @RequestParam("viewAccountName") String viewAccountName,
            @RequestParam("status") String status,
            @RequestParam("type") String type,
            Model model

    ) {

        User user = new User();

        LocalDateTime now = LocalDateTime.now();

        user.setUserName(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setLinkAccountName(linkAccountName);
        user.setViewAccountName(viewAccountName);
        user.setDateOfCreation(now.format(formatter));
        user.setStatus(Enum.valueOf(UserStatus.class, status));
        user.setType(Enum.valueOf(UserType.class, type));

        userRepository.save(user);

        User createdUser = userRepository.findByUserName(username).orElseThrow(EntityNotFoundException::new);

        model.addAttribute("createdUser", createdUser);

        //dokonczyc
        Authority userAuthority = authorityRepository.findAuthoritiesByAuthority("ADMIN").orElseThrow(EntityNotFoundException::new);

        return "/userDatabaseInsert";
    }

    @PostMapping("/deletePost")
    String postDelete(@RequestParam("id") Long id, Model model) {

        entryRepository.deleteById(id);

        entryList = entryRepository.findAll();

        if (entryList.size() == 0) {
            return "emptyWall";
        } else {
            model.addAttribute("entryList", entryList);
            return "wall";
        }
    }

}
