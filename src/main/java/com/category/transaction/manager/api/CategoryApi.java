package com.category.transaction.manager.api;

import com.category.transaction.manager.domain.CategoryTransaction;
import com.category.transaction.manager.domain.CategoryUpdateRequest;
import com.category.transaction.manager.service.TransactionReadService;
import com.category.transaction.manager.service.TransactionUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/v1/categories")
@RestController
public class CategoryApi {

    private final TransactionUpdateService transactionUpdateService;

    private final TransactionReadService transactionReadService;

    @Autowired
    public CategoryApi(TransactionUpdateService transactionUpdateService, TransactionReadService transactionReadService) {
        this.transactionUpdateService = transactionUpdateService;
        this.transactionReadService = transactionReadService;
    }

    @GetMapping("/{categoryId}")
    ResponseEntity<?> getCategoryTransaction(@PathVariable String categoryId) {
        Optional<CategoryTransaction> categoryTransactionResponse = transactionReadService.getCategoryTransaction(categoryId);
        if (!categoryTransactionResponse.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(categoryTransactionResponse, HttpStatus.OK);
    }

    @PutMapping
    ResponseEntity<?> updateCategoryTransaction(@RequestBody CategoryUpdateRequest updateRequest) {
        return transactionUpdateService.transactionCategoryUpdate(updateRequest);
    }
}
