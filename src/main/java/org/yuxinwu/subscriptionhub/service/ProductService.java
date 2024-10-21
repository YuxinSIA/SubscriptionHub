package org.yuxinwu.subscriptionhub.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yuxinwu.subscriptionhub.model.Product;
import org.yuxinwu.subscriptionhub.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductsByCategory(String category) {
        logger.debug("Fetching products by category: {}", category);
        List<Product> products = productRepository.findByCategory(category);
        logger.info("Found {} products in category: {}", products.size(), category);
        return products;
    }

    public Product addProduct(Product product) {
        logger.debug("Adding new product: {}", product.getName());
        Product savedProduct = productRepository.save(product);
        logger.info("Product added successfully: {}", savedProduct.getName());
        return savedProduct;
    }

    public Product updateProductStock(Long productId, int stock) {
        logger.debug("Updating stock for product with ID: {}", productId);
        Product product = productRepository.findById(productId).orElseThrow();
        product.setStock(stock);
        Product updatedProduct = productRepository.save(product);
        logger.info("Updated stock for product '{}'. New stock: {}", product.getName(), stock);
        return updatedProduct;
    }
}

