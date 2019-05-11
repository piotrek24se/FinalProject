package com.nowickipiotr.springfinalproject.repositories;

import com.nowickipiotr.springfinalproject.models.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {
}
