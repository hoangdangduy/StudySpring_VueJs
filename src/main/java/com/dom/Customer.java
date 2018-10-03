package com.dom;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String username;
    private String mail;
    private String password;

    protected Customer() {}

    public Customer(String username, String mail, String password) {
        this.username = username;
        this.mail = mail;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, username='%s', mail='%s',  password='%s']",
                id, username, mail, password);
    }

}