package com.ecom.techie.product.service;

import com.ecom.techie.product.controller.ProductController;
import com.ecom.techie.product.dto.ProductResponse;
import com.ecom.techie.product.model.ProductDetails;
import com.ecom.techie.product.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService
{
    @Autowired
    private ProductRepo productRepo;

    public ProductResponse getProductById(Integer id)
    {
        ProductDetails productDetails = productRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Product Not Found For a Given Id"));
        return ProductResponse.builder()
                .id(productDetails.getId())
                .productName(productDetails.getProductName())
                .price(productDetails.getPrice())
                .description(productDetails.getDescription())
                .brand(productDetails.getBrand()).build();
    }

    public List<ProductResponse> getAllProductsDetails()
    {
        List<ProductDetails> productDetailsList = productRepo.findAll();
        List<ProductResponse> productResponseList = new ArrayList<>();
        for(ProductDetails details : productDetailsList)
        {
            ProductResponse response = ProductResponse.builder()
                    .id(details.getId())
                    .productName(details.getProductName())
                    .price(details.getPrice())
                    .description(details.getDescription())
                    .brand(details.getBrand()).build();
            productResponseList.add(response);
        }

        return productResponseList;

    }

    public List<ProductResponse> createBulkProductDetails(List<ProductResponse> productResponses)
    {
        List<ProductDetails> productDetailsList = new ArrayList<>();
        for(ProductResponse response : productResponses)
        {
            ProductDetails productDetails = ProductDetails.builder()
                    .id(response.getId())
                    .productName(response.getProductName())
                    .price(response.getPrice())
                    .description(response.getDescription())
                    .brand(response.getBrand()).build();
            productDetailsList.add(productDetails);
        }

        List<ProductDetails> savedProductList = productRepo.saveAll(productDetailsList);

        List<ProductResponse> productResponseList = new ArrayList<>();
        for(ProductDetails productDetailsResponse : savedProductList)
        {
            ProductResponse responseDetails = ProductResponse.builder()
                    .id(productDetailsResponse.getId())
                    .productName(productDetailsResponse.getProductName())
                    .price(productDetailsResponse.getPrice())
                    .description(productDetailsResponse.getDescription())
                    .brand(productDetailsResponse.getBrand()).build();
            productResponseList.add(responseDetails);
        }

        return productResponseList;
    }

    public ProductResponse createSingleProduct(ProductResponse productResponse)
    {
        ProductDetails productDetails = ProductDetails.builder()
                .id(productResponse.getId())
                .productName(productResponse.getProductName())
                .price(productResponse.getPrice())
                .description(productResponse.getDescription())
                .brand(productResponse.getBrand())
                .build();
        ProductDetails productDetailsResponse = productRepo.save(productDetails);

        ProductResponse ProductResponseDetails = productResponse.builder()
                .id(productDetailsResponse.getId())
                .productName(productDetailsResponse.getProductName())
                .price(productDetailsResponse.getPrice())
                .description(productDetailsResponse.getDescription())
                .brand(productDetailsResponse.getBrand())
                .build();

        return ProductResponseDetails;
    }

    public ProductResponse updateProductById(Integer id,ProductResponse productResponse)
    {
        ProductDetails productDetails = productRepo.findById(productResponse.getId())
                .orElseThrow(()->new RuntimeException("Given Product Id is not Found"));

        productDetails.setProductName(productResponse.getProductName());
        productDetails.setPrice(productResponse.getPrice());
        productDetails.setDescription(productResponse.getDescription());
        productDetails.setBrand(productResponse.getBrand());

        ProductDetails savedProductEntity = productRepo.save(productDetails);

        ProductResponse responseDetails = ProductResponse.builder()
                .id(savedProductEntity.getId())
                .productName(savedProductEntity.getProductName())
                .price(savedProductEntity.getPrice())
                .description(savedProductEntity.getDescription())
                .brand(savedProductEntity.getBrand())
                .build();

        return responseDetails;

    }

    public List<ProductResponse> updateAllroducts(List<ProductResponse> productResponses)
    {
        List<ProductDetails> productDetailsList = new ArrayList<>();

        for(ProductResponse response : productResponses)
        {
            ProductDetails productDetails = productRepo.findById(response.getId())
                    .orElseThrow(()->new RuntimeException("Given Product Id : "+response.getId()+" is not Found"));

            productDetails.setProductName(response.getProductName());
            productDetails.setPrice(response.getPrice());
            productDetails.setDescription(response.getDescription());
            productDetails.setBrand(response.getBrand());

            productDetailsList.add(productDetails);

        }
        List<ProductDetails> savedProductList = productRepo.saveAll(productDetailsList);

        List<ProductResponse> requestList = new ArrayList<>();
        for(ProductDetails result : savedProductList)
        {
            ProductResponse response = ProductResponse.builder()
                    .id(result.getId())
                    .productName(result.getProductName())
                    .description(result.getDescription())
                    .brand(result.getBrand())
                    .price(result.getPrice()).build();
            requestList.add(response);
        }
        return requestList;

    }

    // Logic to Delete Single Products
    public void deleteProducts(Integer id)
    {
        productRepo.deleteById(id);
    }

    // Logic To Delete List of Products
    public void deleteAllProducts(List<Integer> ids)
    {
        productRepo.deleteAllById(ids);
    }

}
