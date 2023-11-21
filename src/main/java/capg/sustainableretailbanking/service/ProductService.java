package capg.sustainableretailbanking.service;

import capg.sustainableretailbanking.model.ProductModel;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductModel> createProducts(List<ProductModel> products);

    Optional<ProductModel> getProductById(int productId);

    List<ProductModel> getAllProducts();

    void deleteProduct(int productId);

    List<ProductModel> getProductsByCategory(String productCategory);

    List<ProductModel> getProductsBySubCategory(String subCategory);
}
