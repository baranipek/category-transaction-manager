package com.category.transaction.manager.service;

import com.category.transaction.manager.domain.CategoryUpdateRequest;
import org.springframework.http.ResponseEntity;

public interface TransactionUpdateService  {
    ResponseEntity<Void> transactionCategoryUpdate(CategoryUpdateRequest categoryUpdateRequest);
}
