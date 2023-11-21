package capg.sustainableretailbanking.controller;

import capg.sustainableretailbanking.model.PortfolioModel;
import capg.sustainableretailbanking.model.TransactionModel;
import capg.sustainableretailbanking.service.PortfolioService;
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
@RequestMapping("/portfolio")
@CrossOrigin("*")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @Autowired
    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<PortfolioModel>> getList() {
        List<PortfolioModel> portfolio = portfolioService.getList();
        return new ResponseEntity<>(portfolio, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PortfolioModel> getListById(@PathVariable int id) {
        PortfolioModel list = portfolioService.getListById(id);
        if (list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<List<PortfolioModel>> createList(@RequestBody List<PortfolioModel> list) {
        List<PortfolioModel> createdList = portfolioService.createList(list);
        return new ResponseEntity<>(createdList, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteList(@PathVariable int id) {
        boolean deleted = portfolioService.deleteList(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping("/percentages")
    public ResponseEntity<Object> getCategoryPercentages() {
        try {
            Map<String, Double> categoryPercentages = portfolioService.getPercentageAmountSpentInAllCategories();
            double totalAmountSpent = portfolioService.getTotalAmountInvested();

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
