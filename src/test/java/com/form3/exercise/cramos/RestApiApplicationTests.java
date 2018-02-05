package com.form3.exercise.cramos;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.form3.exercise.cramos.dao.PaymentRepository;
import com.form3.exercise.cramos.service.DefaultPaymentService;
import com.form3.exercise.cramos.web.PaymentController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestApiApplicationTests {

	@Autowired
	private PaymentController paymentController;
	@Autowired
	private DefaultPaymentService paymentService;
	@Autowired
	private PaymentRepository paymentRepository;

	@Test
	public void contextLoads() {
		assertThat(paymentController).isNotNull();
		assertThat(paymentService).isNotNull();
		assertThat(paymentRepository).isNotNull();
	}

}
