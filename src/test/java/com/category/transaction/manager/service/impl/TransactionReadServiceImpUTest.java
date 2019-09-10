package com.category.transaction.manager.service.impl;

import com.category.transaction.manager.client.TransactionReadClient;
import com.category.transaction.manager.domain.AuthResponse;
import com.category.transaction.manager.domain.CategoryTransaction;
import com.category.transaction.manager.domain.Transaction;
import com.category.transaction.manager.exception.TokenNotFoundException;
import com.category.transaction.manager.service.AuthenticateService;
import com.category.transaction.manager.service.TransactionReadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionReadServiceImpUTest {

    @InjectMocks
    private TransactionReadServiceImpl transactionReadService;

    @Mock
    private TransactionReadClient transactionReadClient;

    @Mock
    private AuthenticateService authenticateService;

    @Test
    public void validCategoryId_GetCategory_CategoryTransactionInformationIsReturned() {
        // given
        CategoryTransaction categoryTransaction = new CategoryTransaction();
        Transaction transaction = new Transaction();
        transaction.setBankId("bankId");
        categoryTransaction.getResults().add(transaction);

        ResponseEntity<CategoryTransaction> expectedClientResponse =
                new ResponseEntity<>(categoryTransaction, HttpStatus.OK);
        String token = "token";

        when(transactionReadClient.getTransaction(any(), any(), any(), any())).
                thenReturn(expectedClientResponse);
        when(authenticateService.getToken()).thenReturn(Optional.of(token));

        // when
        Optional<CategoryTransaction> actual = transactionReadService.getCategoryTransaction("catId");
        // then
        assertNotNull(actual.isPresent());
        assertEquals(actual.get().getResults().size(),1);
    }


    @Test(expected = TokenNotFoundException.class)
    public void validCategoryIdInvalidToken_GetCategory_TokenExceptionIsThrown() {
        // given
        CategoryTransaction categoryTransaction = new CategoryTransaction();
        Transaction transaction = new Transaction();
        transaction.setBankId("bankId");
        categoryTransaction.getResults().add(transaction);

        when(authenticateService.getToken()).thenReturn(Optional.empty());

        // when
        transactionReadService.getCategoryTransaction("catId");
    }

}