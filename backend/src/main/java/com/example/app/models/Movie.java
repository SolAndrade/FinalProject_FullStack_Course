package com.example.app.models;

import javax.persistence.*;

@Entity
@Table(name = "Movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "genre")
    private String genre;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "age_min")
    private Integer ageMin;

    @Column(name = "tickets")
    private Integer tickets;

    @Column(name = "img_url")
    private String imgUrl;

    public Movie() {
    }

    public Movie(String title, String description, String genre, Integer duration, Double rating, Integer ageMin, Integer tickets, String imgUrl) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
        this.ageMin = ageMin;
        this.tickets = tickets;
        this.imgUrl = imgUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public Integer getTickets() {
        return tickets;
    }

    public void setTickets(Integer tickets) {
        this.tickets = tickets;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
