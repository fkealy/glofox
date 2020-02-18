package com.fkealy.glofox.model;

import java.time.LocalDateTime;

public class Booking {
    private String name;
    private LocalDateTime date;

    public Booking(String name, LocalDateTime date){
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
