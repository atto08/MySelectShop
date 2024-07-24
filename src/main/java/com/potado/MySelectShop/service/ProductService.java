package com.potado.MySelectShop.service;

import com.potado.MySelectShop.dto.ItemDto;
import com.potado.MySelectShop.dto.request.ProductMypriceRequestDto;
import com.potado.MySelectShop.dto.request.ProductRequestDto;
import com.potado.MySelectShop.dto.response.ProductResponseDto;
import com.potado.MySelectShop.entity.Product;
import com.potado.MySelectShop.entity.User;
import com.potado.MySelectShop.entity.UserRoleEnum;
import com.potado.MySelectShop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.SortDirection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public static final int MIN_MY_PRICE = 100;

    public ProductResponseDto createProduct(ProductRequestDto requestDto, User user) {

        Product product = productRepository.save(new Product(requestDto, user));
        return new ProductResponseDto(product);
    }

    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductMypriceRequestDto requestDto) {
        int myprice = requestDto.getMyprice();

        if (myprice < MIN_MY_PRICE) {
            throw new IllegalArgumentException("유효하지 않은 관심 가격입니다. 최소 " + MIN_MY_PRICE + "원 이상으로 설정 해주세요.");
        }

        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 상품을 찾을 수 없습니다."));

        product.update(requestDto);

        return new ProductResponseDto(product);
    }

    public Page<ProductResponseDto> getProducts(User user, int page, int size, String sortBy, boolean isAsc) {

        // 페이지 처리
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

       Page<Product> productList;

       if (user.getRole() == UserRoleEnum.USER){
           productList = productRepository.findAllByUser(user, pageable);
       } else {
           productList = productRepository.findAll(pageable);
       }

       return productList.map(ProductResponseDto::new);
    }

    @Transactional
    public void updateBySearch(Long id, ItemDto itemDto) {

        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 상품을 찾을 수 없습니다."));

        product.updateByItemDto(itemDto);
    }
}
