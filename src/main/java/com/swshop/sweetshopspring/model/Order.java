package com.swshop.sweetshopspring.model;

import com.sun.istack.NotNull;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    @NotBlank
    @NotNull
    private String name;

    @Column(name = "email")
    @NotBlank
    @NotNull
    private String email;

    @Column(name = "chec_checkout_token")
    @NotBlank
    @NotNull
    private String checCheckoutToken;

    @Column(name = "phone_number")
    @NotBlank
    @NotNull
    private String phoneNumber;

    @Column(name = "location")
    @NotBlank
    @NotNull
    private String location;

    @Column(name = "is_completed", columnDefinition = "boolean default false")
    @NotNull
    private Boolean isCompleted = false;


    public Order() {
    }

    public Order(String name, String email, String checCheckoutToken, String phoneNumber, String location, Boolean isCompleted) {
        this.name = name;
        this.email = email;
        this.checCheckoutToken = checCheckoutToken;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.isCompleted = isCompleted;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChecCheckoutToken() {
        return checCheckoutToken;
    }

    public void setChecCheckoutToken(String checCheckoutToken) {
        this.checCheckoutToken = checCheckoutToken;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }
}
