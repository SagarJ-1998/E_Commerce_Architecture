package com.ecom.techie.product.repository;

import com.ecom.techie.product.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<ProductDetails,Integer>
{
    @Override
    Optional<ProductDetails> findById(Integer id);

    @Override
    List<ProductDetails> findAll();

    @Override
    void deleteById(Integer id);

}
