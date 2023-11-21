package capg.sustainableretailbanking.repository;

import capg.sustainableretailbanking.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
    List<ProductModel> findByProductCategory(String productCategory);

    List<ProductModel> findBySubCategory(String subCategory);
}
