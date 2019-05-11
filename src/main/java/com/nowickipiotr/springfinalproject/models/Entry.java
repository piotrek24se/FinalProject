package com.nowickipiotr.springfinalproject.models;

import com.nowickipiotr.springfinalproject.models.enums.EntryStatus;
import com.nowickipiotr.springfinalproject.models.enums.EntryType;

import javax.persistence.*;

@Entity
public class Entry {

    private Long id;
    private String content;
    private String dateOfCreation;

    @Enumerated(EnumType.STRING)
    private EntryStatus status;

    @Enumerated(EnumType.STRING)
    private EntryType type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Entry() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public EntryStatus getStatus() {
        return status;
    }

    public void setStatus(EntryStatus status) {
        this.status = status;
    }

    public EntryType getType() {
        return type;
    }

    public void setType(EntryType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", dateOfCreation='" + dateOfCreation + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", user=" + user +
                '}';
    }
}
