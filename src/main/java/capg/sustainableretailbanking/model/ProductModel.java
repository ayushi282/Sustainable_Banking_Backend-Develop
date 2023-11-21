package capg.sustainableretailbanking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
@Table(name = "products")
public class ProductModel {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int productId;

    @NotBlank(message = "Product name is required")
    @Size(max = 250, message = "Product name must not exceed 250 characters")
    private String productName;

    @NotBlank(message = "Product category is required")
    @Size(max = 250, message = "Product category must not exceed 250 characters")
    private String productCategory;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than or equal to 0.01")
    @DecimalMax(value = "99999.99", message = "Price must be less than or equal to 99999.99")
    private float price;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 1000, message = "Quantity cannot exceed 1000")
    private int quantity;

    @NotNull(message = "Sustainable rating is required")
    @Min(value = 1, message = "	Raiting must be at least 1")
    @Max(value = 5, message = "Raiting cannot exceed 5")
    private int sustainableRating;

    @NotNull(message = "Carbon percentage is required")
    private int carbonPercentage;

    @NotBlank(message = "Transaction ID is required and must not be blank")
    @Size(max = 50, message = "Transaction ID must not exceed 255 characters")
    private String transactionId;

    @NotBlank(message = "Image URL is required and must not be blank")
    @Size(max = 1000, message = "Image URL must not exceed 255 characters")
    private String imageUrl;

    @NotBlank(message = "Product description is required and must not be blank")
    @Size(max = 1000, message = "Product description must not exceed 1000 characters")
    private String productDescription;

    @NotBlank(message = "Product subCategory is required")
    @Size(max = 250, message = "Product subCategory must not exceed 250 characters")
    private String subCategory;

    public ProductModel(int productId, String productName, String productCategory, float price, int quantity,
                        int sustainableRating, int carbonPercentage, String transactionId, String imageUrl, String productDescription, String subCategory ) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.price = price;
        this.quantity = quantity;
        this.sustainableRating = sustainableRating;
        this.carbonPercentage = carbonPercentage;
        this.transactionId = transactionId;
        this.imageUrl = imageUrl;
        this.productDescription = productDescription;
        this.subCategory = subCategory;
    }
}
