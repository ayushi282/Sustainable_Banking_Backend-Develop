package capg.sustainableretailbanking.controller;

import capg.sustainableretailbanking.model.TransactionModel;
import capg.sustainableretailbanking.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transactions")
@CrossOrigin("*")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTransactionById(@PathVariable int id) {
        try {
            TransactionModel transaction = transactionService.getTransactionById(id);
            if (transaction != null) {
                return new ResponseEntity<>(transaction, HttpStatus.OK);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Transaction not found by given ID");
                response.put("message", "Transaction with ID " + id + " not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Internal server error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category")
    public ResponseEntity<Object> getTransactions(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size) {
        try {
            Page<TransactionModel> transactions;

            if (category != null) {
                transactions = transactionService.findByCategoryOrderByTime(category, PageRequest.of(page, size));
            } else {
                transactions = transactionService.getAllTransactionsSortedByTimeAscending(PageRequest.of(page, size));
            }
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Internal server error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getTransactionsByUserId(@PathVariable int userId) {
        try {
            List<TransactionModel> transactions = transactionService.getTransactionsByUserId(userId);
            if (!transactions.isEmpty()) {
                return new ResponseEntity<>(transactions, HttpStatus.OK);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Transactions not found");
                response.put("message", "No transactions found for user with ID " + userId);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Internal server error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTransaction(@Valid @RequestBody List<TransactionModel> transaction) {
        try {
            List<TransactionModel> createdTransaction = transactionService.createTransaction(transaction);
            return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Internal server error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTransaction(
            @PathVariable int id, @RequestBody TransactionModel updatedTransaction) {
        try {
            TransactionModel transaction = transactionService.updateTransaction(id, updatedTransaction);
            if (transaction != null) {
                return new ResponseEntity<>(transaction, HttpStatus.OK);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Transaction not found");
                response.put("message", "Transaction with ID " + id + " not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Internal server error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable int id) {
        try {
            boolean deleted = transactionService.deleteTransaction(id);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Transaction not found");
                response.put("message", "Transaction with ID " + id + " not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Internal server error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categoryPercentages")
    public ResponseEntity<Object> getCategoryPercentages() {
        try {
            Map<String, Double> categoryPercentages = transactionService.getPercentageAmountSpentInAllCategories();
            double totalAmountSpent = transactionService.getTotalAmountSpent();

            if (categoryPercentages.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categoryPercentages, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Internal server error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
