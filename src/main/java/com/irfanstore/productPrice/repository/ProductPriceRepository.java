package com.irfanstore.productPrice.repository;

import com.irfanstore.productPrice.entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice,Long> {
    @Query("select p from  ProductPrice p where p.code = ?1")
    ProductPrice findByProductCode(String productCode);
}
