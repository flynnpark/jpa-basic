package dev.flynnpark;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Address address1 = new Address("Seoul", "Gangnam", "123-123");
            Address address2 = new Address("Seoul", "Gangnam", "123-123");

            System.out.println("address1 equals address2: " + address1.equals(address2));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println(e);
        } finally{
            em.close();
        }
        emf.close();

    }
}
