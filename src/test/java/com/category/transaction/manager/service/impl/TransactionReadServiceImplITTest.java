package com.category.transaction.manager.service.impl;

import com.category.transaction.manager.domain.CategoryTransaction;
import com.category.transaction.manager.service.TransactionReadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TransactionReadServiceImplITTest {

    @Autowired
    private TransactionReadService transactionReadService;

    @Test
    public void validCategoryId_GetCategoryTransaction_CategoryTransactionListIsReturned() {
        //given && when
        Optional<CategoryTransaction> categoryTransaction =
                transactionReadService.getCategoryTransaction("a6hg1");
        //then
        assertTrue(categoryTransaction.isPresent());
        assertEquals(categoryTransaction.get().getResults().size(), 2);
    }
}


