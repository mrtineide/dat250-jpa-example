package no.hvl.dat250.jpa.basicexample.banking;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private Adress adress;
    private CreditCard cc;
}
