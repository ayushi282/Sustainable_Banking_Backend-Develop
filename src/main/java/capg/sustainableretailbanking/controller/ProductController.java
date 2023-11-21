package capg.sustainableretailbanking.controller;

import capg.sustainableretailbanking.model.ProductModel;
import capg.sustainableretailbanking.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/add")
    public ResponseEntity<?> createProducts(@RequestBody List<ProductModel> products) {
        try {
            List<ProductModel> createdProducts = productService.createProducts(products);
            return new ResponseEntity<>(createdProducts, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid input data for adding products", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while adding products", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable int productId) {
        try {
            Optional<ProductModel> product = productService.getProductById(productId);
            return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid input data while getting by ID", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while getting product by ID", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts() {
        try {
            List<ProductModel> products = productService.getAllProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while getting products.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable int productId) {
        try {
            productService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid input data while deleting product.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while deleting product.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category")
    public ResponseEntity<?> getProductsByCategory(@RequestParam("productCategory") String productCategory) {
        try {
            List<ProductModel> products = productService.getProductsByCategory(productCategory);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid input data while getting product by category.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while getting product by category.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/subcategory")
    public ResponseEntity<?> getProductsBySubCategory(@RequestParam("subCategory") String subCategory) {
        try {
            List<ProductModel> products = productService.getProductsBySubCategory(subCategory);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid input data while getting product by category.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while getting product by category.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
