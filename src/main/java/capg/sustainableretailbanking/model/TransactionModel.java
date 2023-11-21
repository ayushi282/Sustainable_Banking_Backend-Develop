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

import java.util.Date; // Import for the transaction date field

@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
@Table(name = "transactions")
public class TransactionModel {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int transactionId;

    private int userId;

    @NotNull(message = "Hours is required")
    @Min(value = 0, message = "Time hour must be at least 0")
    private int time;

    @NotBlank(message = "Company name is required")
    @Size(max = 25, message = "Company name should not exceed 25 characters")
    private String companyName;

    @NotBlank(message = "Category is required")
    private String category;


    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0", message = "Amount must be greater than or equal to 0")
    private double amount;

    @NotBlank(message = "Image URL is required and must not be blank")
    @Size(max = 1000, message = "Image URL must not exceed 255 characters")
    private String imageUrl;

    @NotNull(message = "Sustainable rating is required")
    @Min(value = 1, message = "	Rating must be at least 1")
    @Max(value = 15, message = "Rating cannot exceed 5")
    private int sustainableRating;


    public TransactionModel(int transactionId, int userId, int time,
                            String companyName, double amount, String imageUrl, int sustainableRating) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.time = time;
        this.companyName = companyName;
        this.amount = amount;
        this.imageUrl = imageUrl;
        this.sustainableRating = sustainableRating;
    }
}
