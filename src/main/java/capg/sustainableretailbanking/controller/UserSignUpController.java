package capg.sustainableretailbanking.controller;

import java.util.List;

import capg.sustainableretailbanking.exceptions.UserDeletionException;
import capg.sustainableretailbanking.exceptions.UserRegistrationException;
import capg.sustainableretailbanking.exceptions.UserRetrievalException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import capg.sustainableretailbanking.model.UserSignUpModel;
import capg.sustainableretailbanking.service.UserSignUpServiceImpl;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserSignUpController {

    @Autowired
    UserSignUpServiceImpl userSignUpService;

    @PostMapping("/signup")
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserSignUpModel userSignUp) {
        try {
            String username = userSignUp.getUserName();

            if (userSignUpService.isUsernameAlreadyExists(username)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists.");
            }
            UserSignUpModel user = userSignUpService.addUser(userSignUp);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (UserRegistrationException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allusers")
    public ResponseEntity<List<UserSignUpModel>> getAll() {
        List<UserSignUpModel> userSignUp = userSignUpService.getUser();
        if (userSignUp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userSignUp, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserSignUpModel> getUser(@PathVariable int userId) {
        try {
            UserSignUpModel user = userSignUpService.getUserById(userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserRetrievalException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserDetails(@PathVariable Integer id) {
        try {
            UserSignUpModel user = userSignUpService.getUserById(id);
            if (user == null) {
                return new ResponseEntity<>("User not found with ID - " + id, HttpStatus.NOT_FOUND);
            }
            userSignUpService.deleteUser(id);
            return new ResponseEntity<>("Removed User Details With ID - " + id, HttpStatus.ACCEPTED);
        } catch (UserDeletionException e) {
            return new ResponseEntity<>("User deletion failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid user ID: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
