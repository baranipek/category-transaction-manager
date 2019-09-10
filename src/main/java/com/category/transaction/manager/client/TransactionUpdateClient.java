package com.category.transaction.manager.client;

import com.category.transaction.manager.configuration.ClientConfiguration;
import com.category.transaction.manager.domain.CategoryUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import static com.category.transaction.manager.constant.ApplicationConstant.*;

@FeignClient(name = "transactionUpdateClient", url = "${category.api.categoryUpdate}", configuration = ClientConfiguration.class)
public interface TransactionUpdateClient {
    @PutMapping
    ResponseEntity<Void> updateCategoryTransaction(@RequestHeader(name = AUTHORIZATION_API_KEY) String apiKey,
                                                   @RequestHeader(name = AUTHORIZATION_PARTNER_ID) String partnerId,
                                                   @RequestHeader(name = AUTHORIZATION_TOKEN) String token,
                                                   @RequestBody CategoryUpdateRequest request);
}
