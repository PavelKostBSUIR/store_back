package com.kufar.demo.controller;

import com.kufar.demo.dto.*;
import com.kufar.demo.entity.User;
import com.kufar.demo.mapper.ProductMapper;
import com.kufar.demo.service.FileUploadService;
import com.kufar.demo.service.UserProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/{userId}/products")
public class UserProductController {
    private final ProductMapper productMapper;
    private final UserProductService userProductService;
    private final FileUploadService fileUploadService;

    @Autowired
    UserProductController(ProductMapper productMapper, UserProductService userProductService, FileUploadService fileUploadService) {
        this.userProductService = userProductService;
        this.productMapper = productMapper;
        this.fileUploadService = fileUploadService;
    }

    @GetMapping
    public List<ProductDTO> findAll(Principal principal, @PathVariable("userId") Long userId) {
        return userProductService.findAll(userId, principal).stream().map(productMapper::productToProductDTO).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public ActiveProductDTO findById(@PathVariable("id") Long id, @PathVariable("userId") Long userId, Principal principal) {
        return productMapper.productToActiveProductDTO(userProductService.findById(userId, id, principal));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody @Valid CreateProductDTO createProductDTO, @PathVariable("userId") Long userId, Principal principal) {
        return userProductService.create(userId, productMapper.CreateProductDTOtoProduct(createProductDTO), principal);
    }

    @PostMapping("/{id}/photos")
    @ResponseStatus(HttpStatus.OK)
    public void upload(@PathVariable("userId") Long userId, @PathVariable("id") Long id, @RequestParam("files") MultipartFile[] files, Principal principal) {
        fileUploadService.saveFiles(userId, id, files, principal);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @PathVariable("userId") Long userId, @RequestBody @Valid UpdateProductDTO updateProductDTO, Principal principal) {
        userProductService.update(userId, id, productMapper.UpdateProductDTOtoProduct(updateProductDTO), principal);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id, @PathVariable("userId") Long userId, Principal principal) {
        userProductService.delete(userId, id, principal);
    }

}
