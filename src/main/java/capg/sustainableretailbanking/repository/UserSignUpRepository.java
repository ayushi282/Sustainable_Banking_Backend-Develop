package capg.sustainableretailbanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import capg.sustainableretailbanking.model.UserSignUpModel;

@Repository
public interface UserSignUpRepository extends JpaRepository<UserSignUpModel, Integer> {
    UserSignUpModel findByUserName(String userName);

}
