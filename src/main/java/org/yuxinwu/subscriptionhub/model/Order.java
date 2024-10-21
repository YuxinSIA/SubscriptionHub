package org.yuxinwu.subscriptionhub.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Subscription subscription;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private String status;  // active, canceled, completed

    public Order(User user, Product product, Subscription subscription, LocalDateTime orderDate, String status) {
        this.user = user;
        this.product = product;
        this.subscription = subscription;
        this.orderDate = orderDate;
        this.status = status;
    }
}

