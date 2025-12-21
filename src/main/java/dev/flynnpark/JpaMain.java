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
            Address address = new Address("Seoul", "Gangnam", "123-123");

            Member memberA = new Member();
            memberA.setUsername("memberA");
            memberA.setAddress(address);
            em.persist(memberA);

            Member memberB = new Member();
            memberB.setUsername("memberB");
            memberB.setAddress(address);
            em.persist(memberB);

            // ...

            memberA.getAddress().setCity("Busan"); // A의 주소를 변경하면 B의 주소도 변경된다.
            // 해결책: 임베디드 타입을 변경할 때는 새로운 객체를 만들어 교체해줘야 한다.

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
