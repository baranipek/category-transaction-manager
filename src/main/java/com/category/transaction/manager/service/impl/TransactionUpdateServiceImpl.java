package com.category.transaction.manager.service.impl;

import com.category.transaction.manager.client.TransactionUpdateClient;
import com.category.transaction.manager.domain.CategoryUpdateRequest;
import com.category.transaction.manager.exception.TokenNotFoundException;
import com.category.transaction.manager.service.AuthenticateService;
import com.category.transaction.manager.service.TransactionUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TransactionUpdateServiceImpl implements TransactionUpdateService {
    private final TransactionUpdateClient transactionUpdateClient;
    private final AuthenticateService authenticateService;

    @Autowired
    public TransactionUpdateServiceImpl(TransactionUpdateClient transactionUpdateClient,
                                        AuthenticateService authenticateService) {
        this.transactionUpdateClient = transactionUpdateClient;
        this.authenticateService = authenticateService;
    }

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.partnerId}")
    private String partnerId;

    /**
     * Update category transactions
     * @param categoryUpdateRequest holds category update request
     */
    @Override
    public ResponseEntity<Void> updateCategoryTransaction(CategoryUpdateRequest categoryUpdateRequest) {
        Optional<String> token = authenticateService.getToken();

        if (!token.isPresent()) {
            throw new TokenNotFoundException("token can not be empty");
        }
        String bearerToken = "Bearer " + token.get();
        try {
            return transactionUpdateClient.updateCategoryTransaction(apiKey, partnerId, bearerToken, categoryUpdateRequest);
        } catch (Exception exception) {
            log.error("Category transaction api exception raise" + exception.getLocalizedMessage());
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
