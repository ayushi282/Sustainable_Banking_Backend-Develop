package capg.sustainableretailbanking.service;

import capg.sustainableretailbanking.model.TransactionModel;
import capg.sustainableretailbanking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Page<TransactionModel> findByCategoryOrderByTime(String category, Pageable pageable) {
        try {
            return transactionRepository.findByCategoryOrderByTime(category, pageable);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid input data: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching transactions by category.");
        }
    }

    @Override
    public double getTotalAmountSpent() {
        try {
            List<TransactionModel> transactions = transactionRepository.findAll();
            double totalAmount = transactions.stream()
                    .mapToDouble(TransactionModel::getAmount)
                    .sum();
            return totalAmount;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while calculating the total amount spent.");
        }
    }

    @Override
    public Map<String, Double> getPercentageAmountSpentInAllCategories() {
        List<TransactionModel> allTransactions = transactionRepository.findAll();
        Map<String, Double> categoryAmounts = new HashMap<>();

        for (TransactionModel transaction : allTransactions) {
            String category = transaction.getCategory();
            double amount = transaction.getAmount();

            categoryAmounts.put(category, categoryAmounts.getOrDefault(category, 0.0) + amount);
        }
        double totalAmountSpent = allTransactions.stream().mapToDouble(TransactionModel::getAmount).sum();
        Map<String, Double> categoryPercentagesWithTotal = new HashMap<>();

        for (Map.Entry<String, Double> entry : categoryAmounts.entrySet()) {
            double percentage = (entry.getValue() / totalAmountSpent) * 100;
            categoryPercentagesWithTotal.put(entry.getKey(), percentage);
        }
        categoryPercentagesWithTotal.put("TotalAmountSpent", totalAmountSpent);
        return categoryPercentagesWithTotal;
    }

    @Override
    public TransactionModel getTransactionById(int id) {
        try {
            Optional<TransactionModel> optionalTransaction = transactionRepository.findById(id);
            return optionalTransaction.orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching the transaction by ID.");
        }
    }

    @Override
    public List<TransactionModel> getTransactionsByUserId(int userId) {
        try {
            return transactionRepository.findByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching transactions by user ID.");
        }
    }

    @Override
    public List<TransactionModel> createTransaction(List<TransactionModel> transactions) {
        try {
            return transactionRepository.saveAll(transactions);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Invalid input data: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while creating the transaction(s).");
        }
    }

    @Override
    public TransactionModel updateTransaction(int id, TransactionModel updatedTransaction) {
        try {
            Optional<TransactionModel> optionalTransaction = transactionRepository.findById(id);
            if (optionalTransaction.isPresent()) {
                TransactionModel existingTransaction = optionalTransaction.get();
                existingTransaction.setUserId(updatedTransaction.getUserId());
                existingTransaction.setCompanyName(updatedTransaction.getCompanyName());
                existingTransaction.setAmount(updatedTransaction.getAmount());
                existingTransaction.setTime(updatedTransaction.getTime());
                return transactionRepository.save(existingTransaction);
            } else {
                return null;
            }
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Invalid input data: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the transaction.");
        }
    }

    @Override
    public boolean deleteTransaction(int id) {
        try {
            if (transactionRepository.existsById(id)) {
                transactionRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException("Transaction not found with ID: " + id);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the transaction.");
        }
    }

    @Override
    public Page<TransactionModel> getAllTransactionsSortedByTimeAscending(Pageable pageable) {
        try {
            return transactionRepository.findAllByOrderByTimeAsc(pageable);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching all transactions.");
        }
    }
}