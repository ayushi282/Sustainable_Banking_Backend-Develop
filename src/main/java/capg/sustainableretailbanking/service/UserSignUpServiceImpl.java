package capg.sustainableretailbanking.service;

import java.util.List;

import capg.sustainableretailbanking.exceptions.UserDeletionException;
import capg.sustainableretailbanking.exceptions.UserRegistrationException;
import capg.sustainableretailbanking.exceptions.UserRetrievalException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import capg.sustainableretailbanking.model.UserSignUpModel;
import capg.sustainableretailbanking.repository.UserSignUpRepository;

@Service
public class UserSignUpServiceImpl implements UserSignUpService {

    @Autowired
    private UserSignUpRepository userSignUpRepository;


    @Override
    @Transactional
    public UserSignUpModel addUser(UserSignUpModel userSignUp) {
        try {
            return userSignUpRepository.save(userSignUp);
        } catch (Exception e) {
            throw new UserRegistrationException("Failed to register the user.", e);
        }
    }

    @Override
    public List<UserSignUpModel> getUser() {
        try {
            return userSignUpRepository.findAll();
        } catch (Exception e) {
            throw new UserRetrievalException("Failed to retrieve user data.", e);
        }
    }

    @Override
    public UserSignUpModel getUserById(int userId) {
        return userSignUpRepository.findById(userId)
                .orElseThrow(() -> new UserRetrievalException("User not found with ID " + userId));
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        try {
            userSignUpRepository.deleteById(id);
        } catch (Exception e) {
            throw new UserDeletionException("Failed to delete the user with ID " + id, e);
        }
    }

    @Override
    public boolean isUsernameAlreadyExists(String userName) {
        UserSignUpModel user = userSignUpRepository.findByUserName(userName);
        return user != null;
    }

}
