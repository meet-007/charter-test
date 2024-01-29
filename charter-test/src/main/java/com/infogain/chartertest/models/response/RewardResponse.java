package com.infogain.chartertest.models.response;

import lombok.Data;

import java.util.List;

@Data
public class RewardResponse {
    List<Reward> rewards;
    Long totalRewards;
}
