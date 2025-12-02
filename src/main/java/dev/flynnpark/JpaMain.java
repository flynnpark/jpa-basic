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
            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setCreatedDate(LocalDateTime.now());
            member1.setModifiedDate(LocalDateTime.now());

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setCreatedDate(LocalDateTime.now());
            member2.setModifiedDate(LocalDateTime.now());

            Member member3 = new Member();
            member3.setUsername("member3");
            member3.setCreatedDate(LocalDateTime.now());
            member3.setModifiedDate(LocalDateTime.now());

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
            em.flush();
            em.clear();

            Member foundMember = em.getReference(Member.class, member1.getId());
            System.out.println("Is Loaded: " + emf.getPersistenceUnitUtil().isLoaded(foundMember));
            System.out.println("Reference Member Class: " + foundMember.getClass());
            System.out.println("Member Username: " + foundMember.getUsername());
            System.out.println("Is Loaded: " + emf.getPersistenceUnitUtil().isLoaded(foundMember));
            System.out.println("Member Created Date: " + foundMember.getCreatedDate());

            Member m1 = em.getReference(Member.class, member1.getId());
            Member m2 = em.find(Member.class, member2.getId());
            System.out.println("m1 == m2: " + (m1.getClass() == m2.getClass()));
            System.out.println(("m1 is instance of Member: " + (m1 instanceof Member)));

            Member m3 = em.getReference(Member.class, member3.getId());
            System.out.println("Before accessing m3 username");
            em.detach(m3);

            // 더이상 영속성 컨텍스트에서 관리되지 않으므로 예외 발생
            System.out.println("m3 username: " + m3.getUsername());

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
