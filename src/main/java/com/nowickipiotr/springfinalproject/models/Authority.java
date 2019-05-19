package com.nowickipiotr.springfinalproject.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Authority {

    @Id
    @GeneratedValue
    private Long id;
    private String authority;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;

    public Authority() {
    }

    public Authority(String authority) {
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                ", users=" + users +
                '}';
    }
}
