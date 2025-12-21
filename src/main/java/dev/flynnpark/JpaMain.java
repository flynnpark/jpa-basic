package dev.flynnpark;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setAddress(new Address("city1", "street1", "zipcode1"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("족발");

            member.getAddressHistory().add(new Address("old1", "street1", "zipcode1"));
            member.getAddressHistory().add(new Address("old2", "street2", "zipcode2"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("=== START ===");
            Member findMember = em.find(Member.class, member.getId());
            List<Address> addressHistory = findMember.getAddressHistory();
            for(Address address : addressHistory){
                System.out.println("address = " + address.getCity() + ", " + address.getStreet() + ", " + address.getZipcode());
            }

            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for(String food : favoriteFoods){
                System.out.println("food = " + food);
            }

//            Address newAddress = new Address("old1", "street1", "zipcode1");
//            findMember.getAddressHistory().remove(newAddress);
//            findMember.setAddress(newAddress);

            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            findMember.getAddressHistory().remove(new AddressEntity("old2", "street2", "zipcode2"));
            findMember
                    .getAddressHistory()
                    .add(new AddressEntity("newCity", "newStreet", "newZipcode"));

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
