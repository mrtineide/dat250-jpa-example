package no.hvl.dat250.jpa.basicexample.banking;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToMany
    private List<CreditCard> ccs;

    private String name;

}
