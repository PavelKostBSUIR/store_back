package com.kufar.demo.controller;

import com.kufar.demo.dto.ActiveProductDTO;
import com.kufar.demo.dto.ProductDTO;
import com.kufar.demo.mapper.ProductMapper;
import com.kufar.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductMapper productMapper;
    private final ProductService productService;

    @Autowired
    ProductController(ProductMapper productMapper, ProductService productService) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> findAll(@RequestParam("name") String name, @RequestParam("category") String category, @RequestParam("city") String city) {
        return productService.findAllWithFilter(name, category, city).stream().map(productMapper::productToProductDTO).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public ActiveProductDTO findById(@PathVariable("id") Long id) {

        return productMapper.productToActiveProductDTO(productService.findById(id));
    }

}
