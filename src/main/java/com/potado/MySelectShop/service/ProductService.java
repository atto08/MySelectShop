package com.potado.MySelectShop.service;

import com.potado.MySelectShop.dto.request.ProductRequestDto;
import com.potado.MySelectShop.dto.response.ProductResponseDto;
import com.potado.MySelectShop.entity.Product;
import com.potado.MySelectShop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDto createProduct(ProductRequestDto requestDto) {

        Product product = productRepository.save(new Product(requestDto));
        return new ProductResponseDto(product);
    }
}
