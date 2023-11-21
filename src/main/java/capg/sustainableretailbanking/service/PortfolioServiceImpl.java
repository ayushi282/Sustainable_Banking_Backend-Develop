package capg.sustainableretailbanking.service;

import capg.sustainableretailbanking.model.PortfolioModel;
import capg.sustainableretailbanking.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;

    @Override
    public List<PortfolioModel> getList() {
        return portfolioRepository.findAll();
    }

    @Override
    public PortfolioModel getListById(int id) {
        Optional<PortfolioModel> optionalCart = portfolioRepository.findById(id);
        return optionalCart.orElse(null);
    }
    @Override
    public double getTotalAmountInvested() {
        try {
            List<PortfolioModel> portfolio = portfolioRepository.findAll();
            double totalAmount = portfolio.stream()
                    .mapToDouble(PortfolioModel::getAmountInvested)
                    .sum();
            System.out.print(totalAmount);
            return totalAmount;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while calculating the total amount spent.");
        }
    }

    @Override
    public Map<String, Double> getPercentageAmountSpentInAllCategories() {
        List<PortfolioModel> allPortfolio = portfolioRepository.findAll();
        Map<String, Double> categoryAmounts = new HashMap<>();

        for (PortfolioModel transaction : allPortfolio) {
            String category = transaction.getCategory();
            double amount = transaction.getAmountInvested();

            categoryAmounts.put(category, categoryAmounts.getOrDefault(category, 0.0) + amount);
        }
        double totalAmountSpent = allPortfolio.stream().mapToDouble(PortfolioModel::getAmountInvested).sum();
        Map<String, Double> categoryPercentagesWithTotal = new HashMap<>();

        for (Map.Entry<String, Double> entry : categoryAmounts.entrySet()) {
            double percentage = (entry.getValue() / totalAmountSpent) * 100;
            categoryPercentagesWithTotal.put(entry.getKey(), percentage);
        }
        categoryPercentagesWithTotal.put("TotalAmountSpent", totalAmountSpent);
        return categoryPercentagesWithTotal;
    }



    @Override
    public List<PortfolioModel> createList(List<PortfolioModel> list) {
        return portfolioRepository.saveAll(list);
    }

    @Override
    public boolean deleteList(int id) {
        if (portfolioRepository.existsById(id)) {
            portfolioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}