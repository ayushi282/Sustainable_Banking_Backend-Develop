package capg.sustainableretailbanking.controller;

import capg.sustainableretailbanking.exceptions.UserLoginException;
import capg.sustainableretailbanking.service.UserLoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import capg.sustainableretailbanking.model.UserSignUpModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserLoginController {

    @Autowired
    private UserLoginService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserSignUpModel user) {
        String username = user.getUserName();
        String password = user.getPassword();
        try {
            UserSignUpModel loggedInUser = userService.login(username, password);

            if (loggedInUser != null) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
            }
        } catch (UserLoginException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid login data: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }
}
