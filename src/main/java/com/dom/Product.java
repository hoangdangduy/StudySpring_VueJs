package com.dom;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String nameProduct;
    private float rank;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Comment> lstComment;

    public Product() {}

    public Product(String nameProduct, float rank, List<Comment> lstComment) {
        this.nameProduct = nameProduct;
        this.rank = rank;
        this.lstComment = lstComment;
    }
}
