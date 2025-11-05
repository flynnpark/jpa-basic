package dev.flynnpark;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //            Member member = em.find(Member.class, 1L);
            //            System.out.println("member.id= " + member.getId());
            //            System.out.println("member.name" + member.getName());
            //            member.setName("HelloJPA");

            List<Member> members = em.createQuery("select m from Member as m", Member.class).getResultList();
            for (Member member : members) {
                System.out.println("member.name: " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }
        emf.close();

    }
}
