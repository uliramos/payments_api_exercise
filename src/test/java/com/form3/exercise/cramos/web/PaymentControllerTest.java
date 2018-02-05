package com.form3.exercise.cramos.web;

import static com.form3.exercise.cramos.TestUtils.getPaymentAttributesForTest;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.form3.exercise.cramos.exception.RESTResourceNotFoundException;
import com.form3.exercise.cramos.model.Attributes;
import com.form3.exercise.cramos.model.Payment;
import com.form3.exercise.cramos.service.DefaultPaymentService;
import com.form3.exercise.cramos.service.PaymentService;

/**
 * NOTE: For the context of this exercise and to improve testing/development speed I have decided to implement testing
 * at the web layer from the Spring to the controller layer.
 *
 * The actual HTTTP protocol interaction could have been tested with Spring-boot's internal HTTP container.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Test
    public void shouldGetAllPaymentRecords() throws Exception {
        //given
        Attributes attributes =
                getPaymentAttributesForTest("testPaymentId", "31926819",
                        "GB29XABC10161234567801", 1.00, 3.00,
                        "56781234");
        UUID organisationId = UUID.randomUUID();
        Payment payment = new Payment(1, organisationId, attributes);

        Attributes secondAttributes =
                getPaymentAttributesForTest("secondTestPaymentId", "31926800",
                        "secondGB29XABC10161234567801", 1.00, 3.00,
                        "56781200");
        UUID secondOrganisationId = UUID.randomUUID();
        Payment secondPayment = new Payment(1, secondOrganisationId, secondAttributes);

        given(paymentService.getAll()).willReturn(
                Arrays.asList(DefaultPaymentService.unwiredJPAEntities(payment),
                        DefaultPaymentService.unwiredJPAEntities(secondPayment)));

        // when and then
        this.mockMvc.perform(
                get("/payments"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(2)));
    }

    @Test
    public void shouldGetOnePaymentRecords() throws Exception {
        //given
        Attributes attributes =
                getPaymentAttributesForTest("testPaymentId", "31926819",
                        "GB29XABC10161234567801", 1.00, 3.00,
                        "56781234");
        UUID organisationId = UUID.randomUUID();
        Payment payment = new Payment(1, organisationId, attributes);
        payment.setId(UUID.randomUUID());
        given(paymentService.getOne(payment.getId())).willReturn(DefaultPaymentService.unwiredJPAEntities(payment));

        // when and then
        this.mockMvc.perform(
                get("/payments" + "/" + payment.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(payment.getId().toString()));
    }

    @Test
    public void shouldCreateAPaymentRecord() throws Exception {
        //given
        Attributes attributes =
                getPaymentAttributesForTest("testPaymentId", "31926819",
                        "GB29XABC10161234567801", 1.00, 3.00,
                        "56781234");
        UUID organisationId = UUID.randomUUID();
        Payment payment = new Payment(1, organisationId, attributes);
        payment.setId(UUID.randomUUID());

        // when and then
        given(paymentService.create(any(Payment.class))).willReturn(DefaultPaymentService.unwiredJPAEntities(payment));

        this.mockMvc.perform(
                post("/payments").contentType(MediaType.APPLICATION_JSON_UTF8).content(paymentAsJsonString(payment)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(payment.getId().toString()));
    }

    @Test
    public void shouldUpdateAPaymentRecord() throws Exception {
        //given
        Attributes attributes =
                getPaymentAttributesForTest("testPaymentId", "31926819",
                        "GB29XABC10161234567801", 1.00, 3.00,
                        "56781234");
        UUID organisationId = UUID.randomUUID();
        Payment payment = new Payment(1, organisationId, attributes);
        payment.setId(UUID.randomUUID());


        // when
        given(paymentService.create(any(Payment.class))).willReturn(DefaultPaymentService.unwiredJPAEntities(payment));
        payment.setOrganisationId(UUID.randomUUID()); // updated payment resource

        // when and then
        this.mockMvc.perform(
                put("/payments" + "/" + payment.getId()).contentType(MediaType.APPLICATION_JSON_UTF8).content(paymentAsJsonString(payment)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(payment.getId().toString()))
                .andExpect(jsonPath("$.data.organisation_id").value(payment.getOrganisationId().toString()));
    }

    @Test
    public void shouldDeleteAPaymentRecord() throws Exception {
        //given
        Attributes attributes =
                getPaymentAttributesForTest("testPaymentId", "31926819",
                        "GB29XABC10161234567801", 1.00, 3.00,
                        "56781234");
        UUID organisationId = UUID.randomUUID();
        Payment payment = new Payment(1, organisationId, attributes);
        payment.setId(UUID.randomUUID());

        // when and then
        this.mockMvc.perform(
                delete("/payments" + "/" + payment.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().bytes(new byte[0]));
    }

    @Test
    public void shouldHandleResourceNotFound() throws Exception {
        //given
        // empty repository

        // when
        given(paymentService.getOne(any(UUID.class))).willThrow(
                new RESTResourceNotFoundException("Thrown from Web Layer Test"));

        // when and then
        this.mockMvc.perform(
                get("/payments" + "/" + UUID.randomUUID()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    // helper methods //

    private String paymentAsJsonString(Payment payment) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String jsonString = ow.writeValueAsString(payment);

        return jsonString;
    }
}
