package com.infogain.chartertest.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class TransactionServiceImplTest {


    @Mock
    TransactionServiceImpl transactionService;

    private Method getCalculateRewardMethod() throws NoSuchMethodException {
        Method method = TransactionServiceImpl.class.getDeclaredMethod("calculateReward", Integer.class);
        method.setAccessible(true);
        return method;
    }

    @Test
    void testCalculateRewardMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        assertEquals(50, getCalculateRewardMethod().invoke(transactionService,100 ));
    }

    @Test
    void testCalculateRewardMethodWithNegative() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        assertEquals(0, getCalculateRewardMethod().invoke(transactionService,-1 ));
    }

}