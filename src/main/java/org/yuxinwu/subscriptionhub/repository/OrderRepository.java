package org.yuxinwu.subscriptionhub.repository;

import org.yuxinwu.subscriptionhub.model.Order;
import org.yuxinwu.subscriptionhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}

