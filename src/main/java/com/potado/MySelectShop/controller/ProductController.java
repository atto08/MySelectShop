package com.potado.MySelectShop.controller;

import com.potado.MySelectShop.dto.request.ProductMypriceRequestDto;
import com.potado.MySelectShop.dto.request.ProductRequestDto;
import com.potado.MySelectShop.dto.response.ProductResponseDto;
import com.potado.MySelectShop.security.UserDetailsImpl;
import com.potado.MySelectShop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return productService.createProduct(requestDto, userDetails.getUser());
    }

    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct(@PathVariable(value = "id") Long id,
                                            @RequestBody ProductMypriceRequestDto requestDto) {

        return productService.updateProduct(id, requestDto);
    }

    @GetMapping("/products")
    public Page<ProductResponseDto> getProducts(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return productService.getProducts(userDetails.getUser(), page - 1, size, sortBy, isAsc);
    }

    @PostMapping("/products/{product-id}/folder")
    public void addFolder(@PathVariable(value = "product-id") Long productId,
                          @RequestParam Long folderId,
                          @AuthenticationPrincipal UserDetailsImpl userDetails) {

        productService.addFolder(productId, folderId, userDetails.getUser());
    }

    @GetMapping("/folders/{folder-id}/products")
    public Page<ProductResponseDto> getProductsInFolder(
            @PathVariable(value = "folder-id") Long id,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return productService.getProductsInFolder(
                id,
                page - 1,
                size,
                sortBy,
                isAsc,
                userDetails.getUser());
    }
}
