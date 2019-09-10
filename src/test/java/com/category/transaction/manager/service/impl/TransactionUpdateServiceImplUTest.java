
package com.category.transaction.manager.service.impl;

import com.category.transaction.manager.client.TransactionUpdateClient;
import com.category.transaction.manager.domain.CategoryUpdateRequest;
import com.category.transaction.manager.exception.TokenNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionUpdateServiceImplUTest {
    @Mock
    private TransactionUpdateClient transactionUpdateClient;
    @Mock
    private AuthenticateServiceImpl authenticateService;

    @InjectMocks
    private TransactionUpdateServiceImpl transactionUpdateService;

    @Test
    public void categoryUpdateRequest_UpdateCategoryTransaction_TransactionIsUpdated() {
        // given && when
        CategoryUpdateRequest categoryUpdateRequest = new CategoryUpdateRequest();
        categoryUpdateRequest.setCategoryId("catId");
        when(transactionUpdateClient.updateCategoryTransaction(any(), any(), any(), any())).
                thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        String token = "token";

        when(authenticateService.getToken()).thenReturn(Optional.of(token));
        ResponseEntity<Void> actual = transactionUpdateService.transactionCategoryUpdate(categoryUpdateRequest);

        // then
        assertEquals(actual.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test(expected = TokenNotFoundException.class)
    public void categoryUpdateRequestWithoutToken_UpdateCategoryTransaction_TransactionIsUpdated() {
        // given && when
        CategoryUpdateRequest categoryUpdateRequest = new CategoryUpdateRequest();
        categoryUpdateRequest.setCategoryId("catId");

        when(authenticateService.getToken()).thenReturn(Optional.empty());
        transactionUpdateService.transactionCategoryUpdate(categoryUpdateRequest);
    }
}
