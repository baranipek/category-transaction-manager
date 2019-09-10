package com.category.transaction.manager.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class CategoryTransactionApiException extends RuntimeException{
    private static final long serialVersionUID = -8554973562462309547L;
    private String error;
}