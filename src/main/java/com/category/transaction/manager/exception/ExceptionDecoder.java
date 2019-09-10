package com.category.transaction.manager.exception;


import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.apache.http.HttpStatus;

import java.io.IOException;

public class ExceptionDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (HttpStatus.SC_OK != response.status() ) {
            try {
                byte[] bodyData = Util.toByteArray(response.body().asInputStream());
                String responseBody = new String(bodyData);

                return mapper.readValue(responseBody, CategoryTransactionApiException.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
}