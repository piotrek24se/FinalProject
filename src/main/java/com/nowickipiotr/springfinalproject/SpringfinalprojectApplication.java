package com.nowickipiotr.springfinalproject;

import com.nowickipiotr.springfinalproject.models.Entry;
import com.nowickipiotr.springfinalproject.models.User;
import com.nowickipiotr.springfinalproject.models.enums.EntryStatus;
import com.nowickipiotr.springfinalproject.models.enums.EntryType;
import com.nowickipiotr.springfinalproject.models.enums.UserStatus;
import com.nowickipiotr.springfinalproject.models.enums.UserType;
import com.nowickipiotr.springfinalproject.repositories.EntryRepository;
import com.nowickipiotr.springfinalproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringfinalprojectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringfinalprojectApplication.class, args);
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntryRepository entryRepository;

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User();
        user1.setUserName("Adam");
        user1.setPassword("1111");
        user1.setDateOfCreation("11.05.2019");
        user1.setLinkAccountName("user1");
        user1.setStatus(UserStatus.ACTIVE);
        user1.setType(UserType.PRIVATE);
        user1.setViewAccountName("user1");

        Entry entry1 = new Entry();
        entry1.setContent("test");
        entry1.setDateOfCreation("10.05.2019");
        entry1.setStatus(EntryStatus.ORIGINAL);
        entry1.setType(EntryType.ENTRY);

        Entry entry2 = new Entry();
        entry2.setContent("test");
        entry2.setDateOfCreation("10.05.2019");
        entry2.setStatus(EntryStatus.MODIFIED);
        entry2.setType(EntryType.COMMENT);

        Set<Entry> entrySet = new HashSet<>();
        entrySet.add(entry1);
        entrySet.add(entry2);

        user1.setEntries(entrySet);
        entry1.setUser(user1);
        entry2.setUser(user1);

        userRepository.save(user1);
        entryRepository.saveAll(entrySet);

    }
}
