package capg.sustainableretailbanking.service;

import java.util.List;

import capg.sustainableretailbanking.model.UserSignUpModel;

public interface UserSignUpService {
    public UserSignUpModel addUser(UserSignUpModel userSignUp);

    public List<UserSignUpModel> getUser();

    UserSignUpModel getUserById(int userId);

    public void deleteUser(int id);

    public boolean isUsernameAlreadyExists(String userName);


}
