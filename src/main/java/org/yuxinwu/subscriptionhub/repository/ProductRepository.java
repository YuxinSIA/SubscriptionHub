package org.yuxinwu.subscriptionhub.repository;

import org.yuxinwu.subscriptionhub.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
}

