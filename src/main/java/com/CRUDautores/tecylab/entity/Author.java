package com.CRUDautores.tecylab.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length=100)
    private String firstName;
    @Column(nullable = false, length=100)
    private String lastName;
    @Column(nullable = false, length=120)
    private String email;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private Status authorStatus;

}
