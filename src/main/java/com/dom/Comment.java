package com.dom;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String username;
    private String comment;
    private int rank;

    @ManyToOne
    @JoinColumn(name = "idProduct", referencedColumnName="id")
    @JsonBackReference
    private Product product;

    public Comment() {}

    public Comment(String username, String comment, int rank, Product product) {
        this.username = username;
        this.comment = comment;
        this.rank = rank;
        this.product = product;
    }

    public Comment(String username, String comment, int rank) {
        this.username = username;
        this.comment = comment;
        this.rank = rank;
    }
}
