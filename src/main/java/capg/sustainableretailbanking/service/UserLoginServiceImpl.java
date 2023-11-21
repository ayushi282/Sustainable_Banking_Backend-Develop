package capg.sustainableretailbanking.service;

import capg.sustainableretailbanking.exceptions.UserLoginException;
import capg.sustainableretailbanking.model.UserSignUpModel;
import capg.sustainableretailbanking.repository.UserSignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserSignUpRepository userRepository;

    @Override
    public UserSignUpModel login(String username, String password) {
        UserSignUpModel user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UserLoginException("User not found");
        }

        if (!user.getPassword().equals(password)) {
            throw new UserLoginException("Incorrect password");
        }
        return user;
    }

}
