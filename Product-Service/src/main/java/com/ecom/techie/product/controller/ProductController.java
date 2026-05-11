package com.ecom.techie.product.controller;

import com.ecom.techie.product.dto.ProductResponse;
import com.ecom.techie.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController
{

    @Autowired
    private ProductService productService;

// API To Get Single Product
    @GetMapping("/getproduct/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id)
    {
        if(id!=null) {
            ProductResponse response = productService.getProductById(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product Id not Found");
        }
    }

// API To Get Bulk Product
    @GetMapping("/getBulk")
    public ResponseEntity<?> getAllProducts()
    {
        try {
            List<ProductResponse> productResponseList = productService.getAllProductsDetails();
            return ResponseEntity.status(HttpStatus.OK).body(productResponseList);
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // 404 Not Found if product missing
        }
    }


// API To Create Bulk Product
    @PostMapping("/createBulk")
    public ResponseEntity<?> createBulkProduct(@RequestBody List<ProductResponse> productResponse)
    {
        try{
           List<ProductResponse> responses = productService.createBulkProductDetails(productResponse);
            return ResponseEntity.status(HttpStatus.CREATED).body(responses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Products List are not created");
        }
    }

// API To Create Single Product
   @PostMapping("/createProduct")
   public ResponseEntity<?> createproduct(@RequestBody ProductResponse productResponse)
   {
        try
        {
            ProductResponse response = productService.createSingleProduct(productResponse);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Products List are not created");
        }
   }

// API To Update Single Product
    @PostMapping("/updateProduct/{id}")
    public ResponseEntity<?> updateSingleProduct(@PathVariable Integer id, @RequestBody ProductResponse productResponse)
    {
        if (id==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Provided Products Id : "+ id + " is not Found");
        }
        else{
            try{
                productService.updateProductById(id,productResponse);
                return ResponseEntity.status(HttpStatus.OK).body("Products Updated successfully");
            }catch (Exception e) {
                return ResponseEntity.notFound().build(); // 404 Not Found if product missing
            }
        }
    }

    // API to Update List Of Products Details
    @PutMapping("/updateBulk")
    public ResponseEntity<?> updateBulkProduct(@RequestBody List<ProductResponse> productRequest)
    {
        try{
            productService.updateAllroducts(productRequest);
            return ResponseEntity.status(HttpStatus.OK).body("Products Updated successfully");
        }catch (Exception e) {
            return ResponseEntity.notFound().build(); // 404 Not Found if product missing
        }
    }

// API to Delete Single Product
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id)
    {
        if(id==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product Id is not Available");
        }
        try{
            productService.deleteProducts(id);
            return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // 404 Not Found if product missing
        }
    }

// API to Delete Bulk Products
    @DeleteMapping("/deleteBulk")
    public ResponseEntity<?> deleteBulkProduct(@RequestBody List<Integer> ids)
    {
        try{
            productService.deleteAllProducts(ids);
            return ResponseEntity.status(HttpStatus.OK).body("All Products deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // 404 Not Found if product missing
        }
    }

//    POST   /products
//    GET    /products
//    GET    /products/{id}
//    PUT    /products/{id}
//    DELETE /products/{id}
//    DELETE /products/deleteBulk

}
