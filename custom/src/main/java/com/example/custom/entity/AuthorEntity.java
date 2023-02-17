package com.example.custom.entity;


import com.example.custom.entity.listener.AuthorEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "AUTHOR")
@EntityListeners(AuthorEntityListener.class)
public class AuthorEntity {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME", nullable = false)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
