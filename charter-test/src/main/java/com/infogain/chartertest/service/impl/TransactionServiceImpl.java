package com.infogain.chartertest.service.impl;

import com.infogain.chartertest.entity.Customer;
import com.infogain.chartertest.entity.Reward;
import com.infogain.chartertest.entity.Transaction;
import com.infogain.chartertest.exceptions.BadRequestException;
import com.infogain.chartertest.models.request.TransactionRequest;
import com.infogain.chartertest.repository.CustomerRepository;
import com.infogain.chartertest.repository.TransactionRepository;
import com.infogain.chartertest.service.RewardService;
import com.infogain.chartertest.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    private CustomerRepository customerRepository;


    private RewardService rewardService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, RewardService rewardService,CustomerRepository customerRepository){
        this.transactionRepository = transactionRepository;
        this.rewardService = rewardService;
        this.customerRepository = customerRepository;
    }

    @Override
    public void saveTransaction(TransactionRequest transactionRequest) throws BadRequestException {
        Transaction transaction = new Transaction();
        transaction.setTransactionTime(LocalDateTime.now());
        transaction.setTransactionValue(transactionRequest.getTransactionValue());
        Customer customer = customerRepository.findById(transactionRequest.getCustomerId()).orElseThrow(() -> new BadRequestException("customer not found"));
        transaction.setCustomer(customer);
        transaction = transactionRepository.save(transaction);
        rewardService.saveReward(transaction,calculateReward(transaction.getTransactionValue()));
    }

    @Override
    public Transaction updateTransaction(TransactionRequest transactionRequest) throws BadRequestException {
        Transaction transaction = transactionRepository.findById(transactionRequest.getId()).orElseThrow(() -> new BadRequestException("Transaction not found"));
        transaction.setTransactionTime(LocalDateTime.now());
        transaction.setTransactionValue(transactionRequest.getTransactionValue());
        transaction = transactionRepository.save(transaction);
        Reward reward = rewardService.getReward(transaction.getId());
        rewardService.updateReward(reward.getId(),calculateReward(transaction.getTransactionValue()));
        return  transaction;
    }

    private int calculateReward(Integer transactionValue){
        int pointsEarnedOverHundred =0,pointsEarnedOverFifty=0;
        if(transactionValue>0){
            if(transactionValue>50){
                pointsEarnedOverFifty = (transactionValue-50);
            }
            if(transactionValue>100){
                pointsEarnedOverHundred = (transactionValue-100)*2;
            }
        }
        return pointsEarnedOverHundred+pointsEarnedOverFifty;
    }
}
