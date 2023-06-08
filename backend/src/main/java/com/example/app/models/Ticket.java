package com.example.app.models;

import javax.persistence.*;

@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /*@Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userEmail;*/

    // Constructors, getters, and setters

    public Ticket() {
    }

    public Ticket(Movie movie, User user) {
        this.movie = movie;
        this.user = user;
    }

    /*public Ticket(Movie movie, String userName, String userEmail) {
        this.movie = movie;
        this.userName = userName;
        this.userEmail = userEmail;
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /*public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }*/


    // Additional methods (if needed)
}
