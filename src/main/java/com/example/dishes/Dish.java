package com.example.dishes;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @Size(min=3)
    private String name;

    @NotEmpty
    @Size(min=10)
    private String description;

    private String imgUrl;

    @OneToMany(mappedBy = "dish")
    private Set<Tasty> tasties;

    @OneToMany(mappedBy = "dish")
    private Set<Nasty> nasties;

    private String img;

    public Dish() {
        tasties = new HashSet<>();
        nasties = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Tasty> getTasties() {
        return tasties;
    }

    public void setTasties(Set<Tasty> tasties) {
        this.tasties = tasties;
    }

    public Set<Nasty> getNasties() {
        return nasties;
    }

    public void setNasties(Set<Nasty> nasties) {
        this.nasties = nasties;
    }
}
