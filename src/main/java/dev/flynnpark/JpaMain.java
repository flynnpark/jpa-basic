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
            member.setCreatedDate(LocalDateTime.now());
            member.setModifiedDate(LocalDateTime.now());

            em.flush();
            em.clear();

            Member foundMember = em.find(Member.class, member.getId());
            System.out.println("Member Created Date: " + foundMember.getCreatedDate());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }
        emf.close();

    }
}
