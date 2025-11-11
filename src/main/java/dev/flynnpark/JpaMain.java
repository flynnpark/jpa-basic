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
            Member member = new Member(1L, "John Doe");
            // 1차 캐시에 저장
            em.persist(member);

            Member findMember1 = em.find(Member.class, 1L);// 1차 캐시에서 조회
            Member findMember2 = em.find(Member.class, 2L);// DB에서 조회
            Member findMember3 = em.find(Member.class, 2L);// 1차 캐시에서 조회
            System.out.println("findMember1.name = " + findMember1.getName());
            System.out.println("findMember2.name = " + findMember2.getName());
            System.out.println("findMember2 == findMember3 = " + (findMember2 == findMember3));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }
        emf.close();

    }
}
