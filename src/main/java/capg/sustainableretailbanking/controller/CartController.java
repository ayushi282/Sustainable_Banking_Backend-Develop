package capg.sustainableretailbanking.controller;

import capg.sustainableretailbanking.model.CartModel;
import capg.sustainableretailbanking.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/carts")
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping("/allcartitems")
    public ResponseEntity<List<CartModel>> getAllproducts() {
        List<CartModel> products = cartService.getAllproducts();
        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    @GetMapping("/getinfo/{userId}")
    public ResponseEntity<List<CartModel>> getProductsByUserId(@PathVariable int userId) {
        List<CartModel> products = cartService.getProductsByUserId(userId);
        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<CartModel> createCart(@RequestBody CartModel product) {
        CartModel createdCart = cartService.createProduct(product);
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        boolean deleted = cartService.deleteProduct(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
