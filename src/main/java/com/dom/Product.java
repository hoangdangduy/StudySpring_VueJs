package com.dom;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String nameProduct;
    private float rank;
    private String comment;

    public Product() {}

    public Product(String nameProduct, float rank, String comment) {
        this.nameProduct = nameProduct;
        this.rank = rank;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nameProduct='" + nameProduct + '\'' +
                ", rank=" + rank +
                ", comment='" + comment + '\'' +
                '}';
    }
}
