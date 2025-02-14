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
            Member member = new Member();
            member.setId(2L);
            member.setUsername("B");
            member.setRoleType(RoleType.ADMIN);

            manager.persist(member);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            manager.close();
        }
        factory.close();
    }
}
