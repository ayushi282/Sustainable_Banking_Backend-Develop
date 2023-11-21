package capg.sustainableretailbanking.repository;

import capg.sustainableretailbanking.model.PortfolioModel;
import capg.sustainableretailbanking.model.TransactionModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<PortfolioModel, Integer> {
    List<PortfolioModel> findByUserId(int userId);



}
