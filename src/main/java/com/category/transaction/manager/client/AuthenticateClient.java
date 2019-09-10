package com.category.transaction.manager.client;

import com.category.transaction.manager.configuration.ClientConfiguration;
import com.category.transaction.manager.domain.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import static com.category.transaction.manager.constant.ApplicationConstant.AUTHORIZATION_API_KEY;
import static com.category.transaction.manager.constant.ApplicationConstant.AUTHORIZATION_PARTNER_ID;


@FeignClient(name = "authenticateClient", url = "${category.api.token}", configuration = ClientConfiguration.class)
public interface AuthenticateClient {

    @PostMapping
    ResponseEntity<AuthResponse> authenticate(@RequestHeader(name = AUTHORIZATION_API_KEY) String apiKey,
                                              @RequestHeader(name = AUTHORIZATION_PARTNER_ID) String partnerId);
}
