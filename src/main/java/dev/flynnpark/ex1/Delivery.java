package dev.flynnpark.ex1;

import jakarta.persistence.*;

@Entity
public class Delivery {
    @Id @GeneratedValue
    private Long id;

    @Embedded
    private Address address;

    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;
}
