package com.category.transaction.manager.service;


import com.category.transaction.manager.domain.CategoryTransaction;

import java.util.Optional;

public interface TransactionReadService {
    Optional<CategoryTransaction> getCategoryTransaction(final String categoryId);
}
