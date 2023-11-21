package capg.sustainableretailbanking.service;

import capg.sustainableretailbanking.model.UserSignUpModel;

public interface UserLoginService {
    public UserSignUpModel login(String username, String password);

}
