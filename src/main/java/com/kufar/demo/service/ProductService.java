package com.kufar.demo.service;

import com.kufar.demo.entity.Product;
import com.kufar.demo.exception.ResourceNotFoundException;
import com.kufar.demo.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllWithFilter(String name, String category, String city) {
        return productRepository.findByNameMatchesAndCategoryAndCity(".*" + name + ".*", category, city
        );
    }

    public Product findById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
