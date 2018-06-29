package com.example.dishes;

import javax.persistence.*;

@Entity
public class Tasty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int count;

    @ManyToOne
    private Dish dish;

    public Tasty() {
        count++;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}
