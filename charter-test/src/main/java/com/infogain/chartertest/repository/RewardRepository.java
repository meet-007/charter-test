package com.infogain.chartertest.repository;

import com.infogain.chartertest.entity.Reward;
import com.infogain.chartertest.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface RewardRepository extends JpaRepository<Reward,Long> {

    Reward findByTransaction(Transaction transaction);


    @Query(value = "SELECT Month(t.transaction_time) as rewardMonth, CASE \n" +
            "    WHEN SUM(r.reward_value) > 0 THEN SUM(r.reward_value) \n" +
            "    ELSE 0 END as total \n" +
            "FROM REWARD  R  join TRANSACTION T ON R.TRANSACTION_ID  = T.ID  JOIN CUSTOMER C ON T.CUSTOMER_ID = C.ID WHERE C.ID = :custId AND FORMATDATETIME(T.TRANSACTION_TIME , 'yyyy-MM-dd') >  :fromDate AND FORMATDATETIME(T.TRANSACTION_TIME , 'yyyy-MM-dd') <= :toDate GROUP BY month(T.TRANSACTION_TIME)", nativeQuery = true)
    List<RewardDetail> getRewardsByDate(@Param("custId") Long custId, @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);

}
