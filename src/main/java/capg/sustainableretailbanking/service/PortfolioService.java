package capg.sustainableretailbanking.service;

import capg.sustainableretailbanking.model.PortfolioModel;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface PortfolioService {

    PortfolioModel getListById(int id);

    List<PortfolioModel> createList(List<PortfolioModel> list);
  
    boolean deleteList(int id);

    List<PortfolioModel> getList();

    public Map<String, Double> getPercentageAmountSpentInAllCategories();

    public double getTotalAmountInvested();


}
