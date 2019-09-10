package com.category.transaction.manager.service;

import java.util.Optional;

public interface AuthenticateService {
    Optional<String> getToken();
}
