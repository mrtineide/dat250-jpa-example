package no.hvl.dat250.jpa.basicexample.banking;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String  id;

    private String name;

    @ManyToMany(mappedBy = "residents")
    private List<Address> address;

    @OneToMany
    private List<CreditCard> cc;
}
