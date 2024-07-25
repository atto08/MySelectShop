package com.potado.MySelectShop.repository;

import com.potado.MySelectShop.entity.Folder;
import com.potado.MySelectShop.entity.Product;
import com.potado.MySelectShop.entity.ProductFolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductFolderRepository extends JpaRepository<ProductFolder, Long> {
    Optional<ProductFolder> findByProductAndFolder(Product product, Folder folder);
}
