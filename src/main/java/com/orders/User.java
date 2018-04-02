package com.orders;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @SequenceGenerator(name="SEQ_GEN_USER", sequenceName="SEQ_GEN_USER", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SEQ_GEN_USER")
    private Long id;

    private String name;

    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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


}