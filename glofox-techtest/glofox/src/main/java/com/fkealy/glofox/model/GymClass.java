package com.fkealy.glofox.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class GymClass {

    private @Id
    @GeneratedValue
    long id;
    private String name;
    private int capacity;
    private LocalDateTime date;

    public GymClass(){};

    public GymClass(String name, int capacity, LocalDateTime date){
        this.name = name;
        this.capacity = capacity;
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
