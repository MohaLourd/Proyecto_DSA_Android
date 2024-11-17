package com.example.restclientservweb;

import java.util.LinkedList;
import java.util.List;

public class User {
    private String username;
    private String password;
    int dinero = 20;
    List<Products> productos;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.productos = new LinkedList<>();

    }

    public User(String username, int dinero) {
        this.username = username;
        this.dinero = dinero;
        this.productos = new LinkedList<>();
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

}