package com.nowickipiotr.springfinalproject.models;

import com.nowickipiotr.springfinalproject.models.enums.UserStatus;
import com.nowickipiotr.springfinalproject.models.enums.UserType;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String password;
    private String linkAccountName;
    private String viewAccountName;
    private String dateOfCreation;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Entry> entries;

    @ManyToMany
    @JoinTable(
            name = "users_authorities",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "authority_id", referencedColumnName = "id"
            )
    )
    private Set<Authority> authorities;

    public User() {
    }

    public User(String userName, String password, String linkAccountName, String viewAccountName, String dateOfCreation, UserStatus status, UserType type) {
        this.userName = userName;
        this.password = password;
        this.linkAccountName = linkAccountName;
        this.viewAccountName = viewAccountName;
        this.dateOfCreation = dateOfCreation;
        this.status = status;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLinkAccountName() {
        return linkAccountName;
    }

    public void setLinkAccountName(String linkAccountName) {
        this.linkAccountName = linkAccountName;
    }

    public String getViewAccountName() {
        return viewAccountName;
    }

    public void setViewAccountName(String viewAccountName) {
        this.viewAccountName = viewAccountName;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Set<Entry> getEntries() {
        return entries;
    }

    public void setEntries(Set<Entry> entries) {
        this.entries = entries;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", linkAccountName='" + linkAccountName + '\'' +
                ", viewAccountName='" + viewAccountName + '\'' +
                ", dateOfCreation='" + dateOfCreation + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", entries=" + entries +
                ", authorities=" + authorities +
                '}';
    }
}
