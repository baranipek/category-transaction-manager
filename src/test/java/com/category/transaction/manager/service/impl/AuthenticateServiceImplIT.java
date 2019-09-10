package com.category.transaction.manager.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthenticateServiceImplIT {

    @Autowired
    private AuthenticateServiceImpl authenticateService;

    @Test
    public void validToken_GetToken_TokenReturnSuccessfully() {
        //given && when
        Optional<String> response = authenticateService.getToken();
        //then
        assertNotNull(response);
    }

}