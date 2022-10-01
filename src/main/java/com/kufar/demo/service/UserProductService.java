package com.kufar.demo.service;

import com.kufar.demo.entity.Product;
import com.kufar.demo.entity.User;
import com.kufar.demo.exception.ResourceNotFoundException;
import com.kufar.demo.repo.ProductRepository;
import com.kufar.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Service
public class UserProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    UserProductService(ProductRepository productRepository, UserRepository userRepository, ProductService productService, UserService userService) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productService = productService;
        this.userService = userService;
    }

    @Transactional
    public List<Product> findAll(Long userId, Principal principal) {
        User user = userService.findById(userId, principal);
        return productRepository.findAllByOwner(user);
    }

    @Transactional
    public Product findById(Long userId, Long productId, Principal principal) {
        User user = userService.findById(userId, principal);
        return productRepository.findByIdAndOwner(productId, user);
    }

    @Transactional
    public Long create(Long userId, Product product, Principal principal) {
        User user = userService.findById(userId, principal);
        product.setOwner(user);
        Product savedProduct = productRepository.save(product);
        return savedProduct.getId();

    }

    @Transactional
    public void update(Long userId, Long productId, Product product, Principal principal) {
        Product storedProduct = findById(userId, productId, principal);
        product.setId(storedProduct.getId());
        product.setOwner(storedProduct.getOwner());
        productRepository.save(product);

    }

    @Transactional
    public void delete(Long userId, Long productId, Principal principal) {
        Product storedProduct = findById(userId, productId, principal);
        productRepository.delete(storedProduct);
    }
}
