package com.infogain.chartertest.controller;

import com.infogain.chartertest.models.response.RewardResponse;
import com.infogain.chartertest.service.RewardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reward")
public class RewardController {

    Logger logger = LoggerFactory.getLogger(RewardController.class);

    private RewardService rewardService;

    public RewardController(RewardService rewardService){
        this.rewardService = rewardService;
    }

    @GetMapping
    ResponseEntity<RewardResponse> getRewards(@RequestParam Long custId){
        RewardResponse response = rewardService.getRewards(custId);
        return ResponseEntity.ok(response);
    }


}
