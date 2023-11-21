package capg.sustainableretailbanking.service;

import capg.sustainableretailbanking.model.ProductModel;
import capg.sustainableretailbanking.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductModel> createProducts(List<ProductModel> products) {
        try {
            return productRepository.saveAll(products);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid input data: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while creating the products.");
        }
    }


    @Override
    public Optional<ProductModel> getProductById(int productId) {
        try {
            Optional<ProductModel> product = productRepository.findById(productId);
            if (product.isPresent()) {
                return product;
            } else {
                throw new NoSuchElementException("Product not found with ID: " + productId);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid input data: " + e.getMessage());
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Product not found with ID: " + productId);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching the product.");
        }
    }

    @Override
    public List<ProductModel> getAllProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching all products.");
        }
    }

    @Override
    public void deleteProduct(int productId) {
        try {
            productRepository.deleteById(productId);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid input data: " + e.getMessage());
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException("Product not found with ID: " + productId);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the product.");
        }
    }

    @Override
    public List<ProductModel> getProductsByCategory(String productCategory) {
        try {
            List<ProductModel> products = productRepository.findByProductCategory(productCategory);
            if (products.isEmpty()) {
                throw new NoSuchElementException("No products found in category: " + productCategory);
            }
            return products;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No products found in category: " + productCategory);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching products by category.");
        }
    }

    @Override
    public List<ProductModel> getProductsBySubCategory(String subCategory) {
        try {
            List<ProductModel> products = productRepository.findBySubCategory(subCategory);
            if (products.isEmpty()) {
                throw new NoSuchElementException("No products found in category: " + subCategory);
            }
            return products;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("No products found in category: " + subCategory);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching products by category.");
        }
    }
}

