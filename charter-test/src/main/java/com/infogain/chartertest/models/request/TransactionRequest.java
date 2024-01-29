package com.infogain.chartertest.models.request;

import lombok.Data;

@Data
public class TransactionRequest {
    Long id;
    Long customerId;
    Integer transactionValue;

}
