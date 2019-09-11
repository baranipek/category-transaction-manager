package com.category.transaction.manager.client;

import com.category.transaction.manager.configuration.ClientConfiguration;
import com.category.transaction.manager.domain.CategoryTransaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.category.transaction.manager.constant.ApplicationConstant.*;


@FeignClient(name = "transactionReadClient", url = "${category.api.categories}", configuration = ClientConfiguration.class)
public interface TransactionReadClient {

    @RequestMapping(method = RequestMethod.GET, value = "/{categoryId}/transactions")
    ResponseEntity<CategoryTransaction> getTransaction(@RequestHeader(name = AUTHORIZATION_API_KEY) String apiKey,
                                                       @RequestHeader(name = AUTHORIZATION_PARTNER_ID) String partnerId,
                                                       @RequestHeader(name = AUTHORIZATION_TOKEN) String token,
                                                       @PathVariable("categoryId") String categoryId);

}
