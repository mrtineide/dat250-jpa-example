package no.hvl.dat250.jpa.basicexample.banking;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CreditCard {
    // Database fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Pincode pin;
    @OneToOne
    private Bank bank;

    // Own fields
    private int number;
    private int balance;
    private int limit;
}
