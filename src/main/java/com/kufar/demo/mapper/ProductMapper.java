package com.kufar.demo.mapper;

import com.kufar.demo.dto.*;
import com.kufar.demo.entity.Product;
import com.kufar.demo.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO productToProductDTO(Product product);

    ActiveProductDTO productToActiveProductDTO(Product product);

    Product ActiveProductDTOtoProduct(ActiveProductDTO activeProductDTO);

    UserDTO userToUserDTO(User user);

    Product UpdateProductDTOtoProduct(UpdateProductDTO updateProductDTO);

    Product CreateProductDTOtoProduct(CreateProductDTO createProductDTO);
}
