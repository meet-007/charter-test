package com.infogain.chartertest.service;

import com.infogain.chartertest.entity.Transaction;
import com.infogain.chartertest.exceptions.BadRequestException;
import com.infogain.chartertest.models.request.TransactionRequest;

public interface TransactionService {

    void saveTransaction (TransactionRequest transactionRequest) throws BadRequestException;

    Transaction updateTransaction (TransactionRequest transactionRequest) throws BadRequestException;


}
