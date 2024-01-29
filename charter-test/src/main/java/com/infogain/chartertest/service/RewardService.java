package com.infogain.chartertest.service;

import com.infogain.chartertest.entity.Reward;
import com.infogain.chartertest.entity.Transaction;
import com.infogain.chartertest.exceptions.BadRequestException;
import com.infogain.chartertest.models.response.RewardResponse;

public interface RewardService {

    Reward saveReward(Transaction transaction, Integer rewardValue);

    Reward updateReward(Long rewardId, Integer rewardValue) throws BadRequestException;

    Reward getReward(Long transactionId);

    RewardResponse getRewards(Long custId);

}
