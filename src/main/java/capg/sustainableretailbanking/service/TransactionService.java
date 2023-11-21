package capg.sustainableretailbanking.service;

import capg.sustainableretailbanking.model.TransactionModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface TransactionService {

    TransactionModel getTransactionById(int id);

    List<TransactionModel> getTransactionsByUserId(int userId);

    List<TransactionModel> createTransaction(List<TransactionModel> transaction);

    TransactionModel updateTransaction(int id, TransactionModel updatedTransaction);

    boolean deleteTransaction(int id);

    Page<TransactionModel> getAllTransactionsSortedByTimeAscending(Pageable pageable);

    Page<TransactionModel> findByCategoryOrderByTime(String category, Pageable pageable);

    public Map<String, Double> getPercentageAmountSpentInAllCategories();

    public double getTotalAmountSpent();
}
