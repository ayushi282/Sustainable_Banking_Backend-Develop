package capg.sustainableretailbanking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
@Table(name = "carts")
public class CartModel {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int userId;

    private String productName;

    private double price;


    public CartModel(int userId, String productName, double price) {
        this.userId = userId;
        this.productName = productName;
        this.price = price;

    }


}
