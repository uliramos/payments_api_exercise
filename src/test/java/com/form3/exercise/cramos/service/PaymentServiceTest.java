package com.form3.exercise.cramos.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.form3.exercise.cramos.dao.PaymentRepository;
import com.form3.exercise.cramos.exception.RESTResourceNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class PaymentServiceTest {

    @Autowired
    private PaymentService unitUnderTest;

    @MockBean
    private PaymentRepository paymentRepository;

    @Test(expected = RESTResourceNotFoundException.class)
    public void shouldThrowExceptionWhenEntityNotFound() {
        //given
        given(paymentRepository.findOne(any(UUID.class))).willReturn(null); // entity not found

        // when
        unitUnderTest.getOne(UUID.randomUUID());

        // then
        // see above expected exception
    }
}