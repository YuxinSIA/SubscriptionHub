package org.yuxinwu.subscriptionhub.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yuxinwu.subscriptionhub.model.Order;
import org.yuxinwu.subscriptionhub.model.Product;
import org.yuxinwu.subscriptionhub.model.Subscription;
import org.yuxinwu.subscriptionhub.model.User;
import org.yuxinwu.subscriptionhub.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    public Order placeOrder(User user, Product product, Subscription subscription) {
        logger.debug("Placing order for user: {}, product: {}, subscription: {}", user.getEmail(), product.getName(), subscription.getName());
        Order order = new Order(user, product, subscription, LocalDateTime.now(), "active");
        Order savedOrder = orderRepository.save(order);
        logger.info("Order placed successfully for user: {}. Order ID: {}", user.getEmail(), savedOrder.getId());
        return savedOrder;
    }

    public List<Order> getOrdersForUser(User user) {
        logger.debug("Fetching orders for user: {}", user.getEmail());
        List<Order> orders = orderRepository.findByUser(user);
        logger.info("Found {} orders for user: {}", orders.size(), user.getEmail());
        return orders;
    }

    public void cancelOrder(Long orderId) {
        logger.debug("Cancelling order with ID: {}", orderId);
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus("canceled");
        orderRepository.save(order);
        logger.info("Order with ID: {} has been canceled", orderId);
    }

    public List<Order> getAllOrders() {
        logger.debug("Fetching all orders");
        List<Order> orders = orderRepository.findAll();
        logger.info("Found {} orders", orders.size());
        return orders;
    }
}
