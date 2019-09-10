package com.category.transaction.manager.service.impl;

import com.category.transaction.manager.client.TransactionReadClient;
import com.category.transaction.manager.domain.CategoryTransaction;
import com.category.transaction.manager.exception.CategoryIdIsNullException;
import com.category.transaction.manager.exception.TokenNotFoundException;
import com.category.transaction.manager.service.AuthenticateService;
import com.category.transaction.manager.service.TransactionReadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TransactionReadServiceImpl implements TransactionReadService {
    private final AuthenticateService authenticateService;
    private final TransactionReadClient transactionReadClient;

    @Autowired
    public TransactionReadServiceImpl(AuthenticateService authenticateService,
                                      TransactionReadClient transactionReadClient) {
        this.authenticateService = authenticateService;
        this.transactionReadClient = transactionReadClient;
    }

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.partnerId}")
    private String partnerId;

    @Override
    public Optional<CategoryTransaction> getCategoryTransaction(String categoryId) {
        if (categoryId.isEmpty()) {
            throw new CategoryIdIsNullException("Category id can not be call");
        }
        Optional<String> token = authenticateService.getToken();

        if (!token.isPresent()) {
            throw new TokenNotFoundException("token can not be empty");
        }
        String bearerToken = "Bearer " + token.get();
        try {
            ResponseEntity<CategoryTransaction> responseEntity= transactionReadClient.
                    getTransaction(apiKey, partnerId, bearerToken,categoryId);

          return Optional.ofNullable(responseEntity.getBody());
        } catch (Exception exception) {
            log.error("Category transaction api exception raise" + exception.getLocalizedMessage());
        }
        return Optional.empty();
    }
}
