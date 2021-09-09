package no.hvl.dat250.jpa.basicexample.banking;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String  id;
    private String name;

    @ManyToMany
    private ArrayList<Address> address;

    @OneToMany
    private ArrayList<CreditCard> cc;
}