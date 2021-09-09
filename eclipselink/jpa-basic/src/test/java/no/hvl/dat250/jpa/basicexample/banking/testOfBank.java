package no.hvl.dat250.jpa.basicexample.banking;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class testOfBank{

    private static final String PERSISTENCE_UNIT_NAME = "banking";
    private EntityManagerFactory factory;

    @Before
    public void setup() throws Exception {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        // Begin a new local transaction so that we can persist a new entity
        em.getTransaction().begin();

        // read the existing entries
        Query q = em.createQuery("select b from Bank b");
        // Bank should be empty

        // do we have entries?
        boolean createNewEntries = (q.getResultList().size() == 0);

        // No, so lets create new entries
        if (createNewEntries) {
            assertTrue(q.getResultList().size() == 0);
            // Create some instances
            // Make a bank
            Bank bank = new Bank();
            // Person
            Person max = new Person();
            // Address
            Address personAddress = new Address();
            // Two CCs
            CreditCard card1 = new CreditCard();
            CreditCard card2 = new CreditCard();
            // and a pincode
            Pincode pin = new Pincode();
            // And all the arrays needed to make a many relation
            ArrayList<Person> residentsOnAddress = new ArrayList<>();
            ArrayList<Address> listOfAdress = new ArrayList<>();
            ArrayList<CreditCard> maxsCCs = new ArrayList<>();
            ArrayList<CreditCard> bankCCs = new ArrayList<>();



            // Set the fields on
            // pincode
            // Add the pin and count to pincode
            pin.setCount(1);
            pin.setPincode("123");
            // CCs
            // Add the pincode to the ccs
            card1.setPin(pin);
            card2.setPin(pin);
            // CC1
            card1.setNumber(12345);
            card1.setBalance(-5000);
            card1.setLimit(-10000);
            em.persist(pin);
            card1.setBank(bank);
            // CC2
            card2.setNumber(123);
            card2.setBalance(1);
            card2.setLimit(2000);
            card2.setBank(bank);
            // Person
            // Add max name and such
            max.setName("Max Mustermann");
            // Bank
            bank.setName("Pengebank");
            // Address
            personAddress.setStreet("Inndalsveien");
            personAddress.setNumber(28);


            // Add in arrays
            residentsOnAddress.add(max);
            listOfAdress.add(personAddress);
            bankCCs.add(card1);
            bankCCs.add(card2);
            maxsCCs.add(card1);
            maxsCCs.add(card2);


            // Set the arrays in the objects
            // Add creditcards
            max.setCc(maxsCCs);
            bank.setCcs(bankCCs);
            // Add residents to address
            personAddress.setResidents(residentsOnAddress);
            // Add address to Max
            max.setAddress(listOfAdress);

            // Persist the all
            em.persist(bank);
            em.persist(max);
            em.persist(card1);
            em.persist(card2);
            em.persist(personAddress);

        }

        // Commit the transaction, which will cause the entity to
        // be stored in the database
        em.getTransaction().commit();

        // It is always good practice to close the EntityManager so that
        // resources are conserved.
        em.close();

    }

    @Test
    public void checkOneBank() {

        // now lets check the database and see if the created entries are there
        // create a fresh, new EntityManager
        EntityManager em = factory.createEntityManager();

        // Perform a simple query for all the Message entities
        Query q = em.createQuery("select m from Bank m");

        // We should have 40 Persons in the database
        assertTrue(q.getResultList().size() == 1);

        em.close();
    }

}
