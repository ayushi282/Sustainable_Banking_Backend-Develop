package capg.sustainableretailbanking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data
@Table(name = "users")
public class UserSignUpModel {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int userId;

    @NotBlank(message = "Username is required")
    @Size(max = 10, message = "Username must not exceed 10 characters")
    private String userName;

    @NotBlank(message = "First name is required")
    @Size(max = 10, message = "First name must not exceed 10 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 10, message = "Last name must not exceed 10 characters")
    private String lastName;

    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 10, message = "Phone number must be exactly 10 characters")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be a 10-digit number")
    private String phone;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    private String password;


    public UserSignUpModel(int userId, String userName, String firstName, String lastName, String phone, String password) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.password = password;
    }

}
