package com.kufar.demo.repo;

import com.kufar.demo.entity.Product;
import com.kufar.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM product WHERE name REGEXP ?1 AND category =?2 AND city =?3")
    List<Product> findByNameMatchesAndCategoryAndCity(String nameReg, String category, String city);

    List<Product> findAllByOwner(User owner);

    Optional<Product> findById(Long id);

    Product findByIdAndOwner(Long id, User owner);
}
