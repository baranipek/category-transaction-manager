package com.category.transaction.manager.configuration;

import com.category.transaction.manager.exception.ExceptionDecoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Client;
import feign.Logger;
import feign.codec.Decoder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ClientConfiguration {

    @Bean
    public Client feignClient() {
        return new Client.Default(getSSLSocketFactory(),
                new NoopHostnameVerifier()); }

    private SSLSocketFactory getSSLSocketFactory() {
        try {
            TrustStrategy acceptingTrustStrategy = (chain, authType) -> true;
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
            return sslContext.getSocketFactory();
        } catch (Exception exception) {
            throw new RuntimeException("Ssl context problem is raised " + exception);
        }
    }

    @Bean
    public ExceptionDecoder exceptionDecoder() {
        return new ExceptionDecoder();
    }
}
