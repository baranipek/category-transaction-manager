package com.category.transaction.manager.service.impl;

import com.category.transaction.manager.client.AuthenticateClient;
import com.category.transaction.manager.domain.AuthResponse;
import com.category.transaction.manager.service.AuthenticateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
@Slf4j
public class AuthenticateServiceImpl implements AuthenticateService {

    private final AuthenticateClient authenticateClient;

    @Autowired
    public AuthenticateServiceImpl(AuthenticateClient authenticateClient) {
        this.authenticateClient = authenticateClient;
    }

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.partnerId}")
    private String partnerId;

    /**
     * Returns token  with apiKey and partnerId
     *
     * @return Optional<String> returns token
     */
    @Override
    public Optional<String> getToken() {
        try {
            ResponseEntity<AuthResponse> authResponse = authenticateClient.authenticate(apiKey, partnerId);
            if (authResponse.getStatusCode().is2xxSuccessful()) {
                return ofNullable(ofNullable(authResponse.getBody()).get().getAccessToken());
            }
        } catch (Exception exception) {
            log.error("Exception raised getting token" + exception.getLocalizedMessage());
        }
        return Optional.empty();
    }
}
