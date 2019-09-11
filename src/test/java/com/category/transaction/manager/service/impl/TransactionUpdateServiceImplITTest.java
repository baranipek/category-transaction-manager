package com.category.transaction.manager.service.impl;

import com.category.transaction.manager.domain.CategoryUpdateRequest;
import com.category.transaction.manager.service.TransactionUpdateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TransactionUpdateServiceImplITTest {

    @Autowired
    private TransactionUpdateService updateService;

    @Test
    public void validCategoryRequest_UpdateCategoryTransaction_UpdatedSuccessfully() {
        // given
        CategoryUpdateRequest request= new CategoryUpdateRequest();
        request.setCategoryId("a6hg1");
        request.setTransactionId("fakeTrx01");

        assertEquals(updateService.updateCategoryTransaction(request).getStatusCode(), HttpStatus.NO_CONTENT);
    }

}