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
            Movie newMovie = new Movie();
            newMovie.setName("Inception");
            newMovie.setPrice(15000);
            newMovie.setDirector("Christopher Nolan");
            System.out.println("Persisting Movie: " + newMovie.getName() + ", Director: " + newMovie.getDirector());
            em.persist(newMovie);

            em.flush();
            em.clear();

            Movie foundMovie = em.find(Movie.class, newMovie.getId());
            System.out.println("Movie Director: " + foundMovie.getDirector());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally{
            em.close();
        }
        emf.close();

    }
}
