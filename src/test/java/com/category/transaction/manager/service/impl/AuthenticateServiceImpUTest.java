package com.category.transaction.manager.service.impl;

import com.category.transaction.manager.client.AuthenticateClient;
import com.category.transaction.manager.domain.AuthResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AuthenticateServiceImpUTest {
    @InjectMocks
    private AuthenticateServiceImpl authenticateService;

    @Mock
    private AuthenticateClient authenticateClient;

    @Test
    public void validToken_GetToken_ReturnHttpOKWithoutToken() {
        // given
        ResponseEntity<AuthResponse> expectedClientResponse =new ResponseEntity<>(HttpStatus.OK);
        // when
        when(authenticateClient.authenticate(any(),any())).thenReturn(expectedClientResponse);
        Optional<String> expected = authenticateService.getToken();
        //then
        assertNotNull(expected);
    }

    @Test
    public void validToken_GetToken_ReturnHttpOKWithNullBody() {
        // given
        ResponseEntity<AuthResponse> expectedClientResponse =new ResponseEntity<>(null,HttpStatus.OK);
        // when
        when(authenticateClient.authenticate(any(),any())).thenReturn(expectedClientResponse);
        Optional<String> expected = authenticateService.getToken();
        //then
        assertNotNull(expected);
    }

    @Test
    public void validToken_GetToken_ReturnTokenSuccessfully() {
        // given
        AuthResponse expectedTokenBody = new AuthResponse();
        expectedTokenBody.setAccessToken("myToken");
        ResponseEntity<AuthResponse> expectedClientResponse =new ResponseEntity<>(expectedTokenBody,HttpStatus.OK);

        // when
        when(authenticateClient.authenticate(any(),any())).thenReturn(expectedClientResponse);
        Optional<String> expected = authenticateService.getToken();

        //then
        assertNotNull(expected);
        assertTrue(expected.isPresent());
        assertEquals(expected.get(),"myToken");
    }

    @Test
    public void invValidToken_GetToken_ReturnEmptyToken() {
        // given && when
        when(authenticateClient.authenticate(any(),any())).thenThrow(IllegalArgumentException.class);
        Optional<String> expected = authenticateService.getToken();

        //then
        assertNotNull(expected);
        assertFalse(expected.isPresent());
    }

}