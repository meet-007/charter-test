package com.infogain.chartertest.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Data
@Entity
public class Reward {
    @Id
    @GeneratedValue
    Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id")
    Transaction transaction;
    Integer rewardValue;
}
