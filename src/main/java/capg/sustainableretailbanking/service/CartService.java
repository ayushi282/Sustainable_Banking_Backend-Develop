package capg.sustainableretailbanking.service;

import capg.sustainableretailbanking.model.CartModel;

import java.util.List;

public interface CartService {
    List<CartModel> getAllproducts();

    CartModel getProductById(int id);

    List<CartModel> getProductsByUserId(int userId);

    CartModel createProduct(CartModel product);

    boolean deleteProduct(int id);

}
