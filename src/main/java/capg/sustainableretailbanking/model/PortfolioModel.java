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
@Table(name = "portfolio")

public class PortfolioModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int listId;

    private int userId;

    @NotBlank(message = "Company name is required")
    @Size(max = 500, message = "Company name should not exceed 25 characters")
    private String companyName;

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "Amount is required")
    private int amountInvested;

    @NotBlank(message = "Image URL is required and must not be blank")
    @Size(max = 1000, message = "Image URL must not exceed 255 characters")
    private String image;

    @NotNull(message = "Value is required")
    private float stockChange;

    public PortfolioModel(int listId, int userId, String companyName, int amountInvested, String image, float stockChange) {
        this.listId = listId;
        this.userId = userId;
        this.companyName = companyName;
        this.amountInvested = amountInvested;
        this.image = image;
        this.stockChange = stockChange;
    }
}

