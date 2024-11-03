package com.assesment.nimap1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.assesment.nimap1.entity.Category;
import com.assesment.nimap1.entity.Product;
import com.assesment.nimap1.repository.ProductRepository;
import com.assesment.nimap1.service.CategoryService;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public Product saveProduct(Product product) {
        // Ensure the category exists before saving the product
        if (product.getCategory() == null || product.getCategory().getId() == null) {
            throw new IllegalArgumentException("Category ID must be provided");
        }
        // Fetching the category to ensure it exists in the database
        Category category = categoryService.getCategoryById(product.getCategory().getId());
        product.setCategory(category); // Set the fetched category
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product existingProduct = getProductById(id);
        existingProduct.setProductName(product.getProductName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
