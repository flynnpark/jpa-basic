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
            Member member = new Member();
            member.setUsername("member1");
            Period period = new Period();
            period.setStartDate(LocalDateTime.now());
            period.setEndDate(LocalDateTime.now().plusYears(1));
            member.setWorkPeriod(period);
            Address address = new Address();
            address.setCity("Seoul");
            address.setStreet("Gangnam");
            address.setZipcode("123-123");
            member.setAddress(address);
            em.persist(member);
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
