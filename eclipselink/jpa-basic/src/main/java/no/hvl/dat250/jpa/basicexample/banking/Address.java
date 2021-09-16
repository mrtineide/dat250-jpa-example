package no.hvl.dat250.jpa.basicexample.banking;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Address {
    // Database fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany
    private List<Person> residents;

    private int number;
    private String street;
}