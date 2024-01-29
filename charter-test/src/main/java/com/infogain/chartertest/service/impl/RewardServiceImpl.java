package com.infogain.chartertest.service.impl;

import com.infogain.chartertest.entity.Reward;
import com.infogain.chartertest.entity.Transaction;
import com.infogain.chartertest.exceptions.BadRequestException;
import com.infogain.chartertest.models.response.RewardResponse;
import com.infogain.chartertest.repository.RewardDetail;
import com.infogain.chartertest.repository.RewardRepository;
import com.infogain.chartertest.repository.TransactionRepository;
import com.infogain.chartertest.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
public class RewardServiceImpl implements RewardService {

    private RewardRepository rewardRepository;
    private TransactionRepository transactionRepository;

    private static int NO_OF_MONTHS = 2;

    @Autowired
    public RewardServiceImpl(RewardRepository rewardRepository, TransactionRepository transactionRepository){
        this.rewardRepository = rewardRepository;
        this.transactionRepository = transactionRepository;
    }


    public Reward updateReward(Long rewardId, Integer rewardValue) throws BadRequestException {
           Reward existingReward = rewardRepository.findById(rewardId).orElseThrow(() -> new BadRequestException("Reward not found"));
           existingReward.setRewardValue(rewardValue);
           return rewardRepository.save(existingReward);
    }

    @Override
    public Reward getReward(Long transactionId) {
        Transaction transaction = new Transaction();
        transaction.setId(transactionId);
        return rewardRepository.findByTransaction(transaction);
    }

    @Override
    public RewardResponse getRewards(Long custId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDate toDate = now.toLocalDate();
        LocalDate fromDate = toDate.minusMonths(NO_OF_MONTHS);
        List<RewardDetail> rewardDetails = rewardRepository.getRewardsByDate(custId,fromDate,toDate);
        List<com.infogain.chartertest.models.response.Reward> rewards = new ArrayList<>();
        long totalReward =0;
        for(int i =0 ; i<=NO_OF_MONTHS;i++)
        {
            com.infogain.chartertest.models.response.Reward reward = new com.infogain.chartertest.models.response.Reward();
            int monthInt = fromDate.plusMonths(i).getMonthValue();
            String month = Month.of(monthInt).name();
            long rewardValue = 0;
            for(RewardDetail rewardDetail : rewardDetails){
                if(monthInt == rewardDetail.getRewardMonth()){
                    rewardValue = rewardDetail.getTotal();
                    totalReward+=rewardValue;
                    break;
                }
            }
            reward.setRewardMonth(month);
            reward.setRewardValue(rewardValue);
            rewards.add(reward);
        }
        RewardResponse response = new RewardResponse();
        response.setRewards(rewards);
        response.setTotalRewards(totalReward);
        return response;
    }

    public Reward saveReward(Transaction transaction, Integer rewardValue) {
        Reward reward = new Reward();
        reward.setRewardValue(rewardValue);
        reward.setTransaction(transaction);
        return rewardRepository.save(reward);
    }
}
