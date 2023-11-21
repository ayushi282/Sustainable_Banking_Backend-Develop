package capg.sustainableretailbanking.repository;

import capg.sustainableretailbanking.model.TransactionModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Integer> {
    List<TransactionModel> findByUserId(int userId);

    Page<TransactionModel> findByCategoryOrderByTime(String category, Pageable pageable);

    Page<TransactionModel> findAllByOrderByTimeAsc(Pageable pageable);
}
