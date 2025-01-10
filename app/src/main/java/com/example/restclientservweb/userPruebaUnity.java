package com.example.restclientservweb;


public class userPruebaUnity {
    String username;
    int dinero;
    int points;

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public userPruebaUnity(int dinero, int points) {
        this.dinero = dinero;
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public userPruebaUnity( String username,int dinero, int points) {
        this.username = username;
        this.dinero = dinero;
        this.points = points;
    }
}
