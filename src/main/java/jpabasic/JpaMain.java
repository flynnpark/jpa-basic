package jpabasic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hello");
        EntityManager manager = factory.createEntityManager();

        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        try {
            Member member = manager.find(Member.class, 1L);
            member.setName("HelloB");

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            manager.close();
        }
        factory.close();
    }
}
