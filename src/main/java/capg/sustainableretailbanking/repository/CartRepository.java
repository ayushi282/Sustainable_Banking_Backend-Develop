package capg.sustainableretailbanking.repository;

import capg.sustainableretailbanking.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartModel, Integer> {
    List<CartModel> findByUserId(int userId);

}
