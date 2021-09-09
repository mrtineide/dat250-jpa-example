package no.hvl.dat250.jpa.basicexample.banking;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;

@Data
@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToMany
    private ArrayList<CreditCard> ccs;

    private String name;

}
