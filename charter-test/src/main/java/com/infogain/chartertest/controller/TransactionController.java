package com.infogain.chartertest.controller;

import com.infogain.chartertest.entity.Transaction;
import com.infogain.chartertest.exceptions.BadRequestException;
import com.infogain.chartertest.models.request.TransactionRequest;
import com.infogain.chartertest.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private TransactionService transactionService;

    @Autowired
    public TransactionController (TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity saveTransaction(@RequestBody TransactionRequest transactionRequest) throws BadRequestException {
        transactionService.saveTransaction(transactionRequest);
        logger.info("transaction saved !");
        return ResponseEntity.status(201).build();
    }

    @PutMapping
    public ResponseEntity<Transaction> updateTransaction(@RequestBody TransactionRequest transactionRequest) throws BadRequestException {
        Transaction transaction = transactionService.updateTransaction(transactionRequest);
        logger.info("transaction updated !");
        return ResponseEntity.ok(transaction);
    }

}
