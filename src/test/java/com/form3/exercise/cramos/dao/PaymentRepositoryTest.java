package com.form3.exercise.cramos.dao;

import static com.form3.exercise.cramos.TestUtils.getPaymentAttributesForTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.form3.exercise.cramos.model.Payment;
import com.form3.exercise.cramos.model.Attributes;
import com.form3.exercise.cramos.ApplicationConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfiguration.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PaymentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PaymentRepository paymentRepository;

    private int version = 1;

    @Test
    public void whenFindAll() {
        //given
        Attributes attributes = getPaymentAttributesForTest("testPaymentId",
                "31926819", "GB29XABC10161234567801",
                1.00, 3.00,"56781234");
        UUID organisationId = UUID.randomUUID();
        Payment payment = new Payment(version, organisationId, attributes);
        attributes.setPayment(payment); // jpa db relational wiring
        entityManager.persist(payment);
        entityManager.flush();

        Attributes secondAttributes = getPaymentAttributesForTest("secondTestPaymentId",
                "31926800", "secondGB29XABC10161234567801",
                2.00, 4.00,"56781200");
        UUID secondOrganisationId = UUID.randomUUID();
        Payment secondPayment = new Payment(version, secondOrganisationId, secondAttributes);
        secondAttributes.setPayment(secondPayment); // jpa db relational wiring
        entityManager.persist(secondPayment);
        entityManager.flush();

        //when
        List<Payment> payments = paymentRepository.findAll();

        //then
        assertThat(payments.get(0).getOrganisationId()).isEqualTo(organisationId);
        assertThat(payments.get(1).getOrganisationId()).isEqualTo(secondOrganisationId);

        assertThat(payments.get(0).getAttributes().getPaymentId()).isEqualTo(payment.getAttributes().getPaymentId());
        assertThat(payments.get(1).getAttributes().getPaymentId()).isEqualTo(secondPayment.getAttributes().getPaymentId());

        assertThat(payments.get(0).getAttributes().getBeneficiaryParty().getAccountNumber()).isEqualTo(
                attributes.getBeneficiaryParty().getAccountNumber());
        assertThat(payments.get(1).getAttributes().getBeneficiaryParty().getAccountNumber()).isEqualTo(
                secondAttributes.getBeneficiaryParty().getAccountNumber());

        assertThat(payments.get(0).getAttributes().getDebtorParty().getAccountNumber()).isEqualTo(
                attributes.getDebtorParty().getAccountNumber());
        assertThat(payments.get(1).getAttributes().getDebtorParty().getAccountNumber()).isEqualTo(
                secondAttributes.getDebtorParty().getAccountNumber());

        assertThat(payments.get(0).getAttributes().getChargesInformation().getReceiverChargesAmount()).isEqualTo(
                attributes.getChargesInformation().getReceiverChargesAmount());
        assertThat(payments.get(1).getAttributes().getChargesInformation().getReceiverChargesAmount()).isEqualTo(
                secondAttributes.getChargesInformation().getReceiverChargesAmount());

        assertThat(payments.get(0).getAttributes().getChargesInformation().getSenderCharges().get(0).getAmount()).isEqualTo(
                attributes.getChargesInformation().getSenderCharges().get(0).getAmount());
        assertThat(payments.get(1).getAttributes().getChargesInformation().getSenderCharges().get(1).getAmount()).isEqualTo(
                secondAttributes.getChargesInformation().getSenderCharges().get(1).getAmount());

        assertThat(payments.get(0).getAttributes().getSponsorParty().getAccountNumber()).isEqualTo(
                attributes.getSponsorParty().getAccountNumber());
        assertThat(payments.get(1).getAttributes().getSponsorParty().getAccountNumber()).isEqualTo(
                secondAttributes.getSponsorParty().getAccountNumber());
    }

    @Test
    public void whenFindOne() {
        //given
        Attributes attributes =
                getPaymentAttributesForTest("testPaymentId", "31926819",
                        "GB29XABC10161234567801", 1.00, 3.00,
                        "56781234");
        UUID organisationId = UUID.randomUUID();
        Payment payment = new Payment(version, organisationId, attributes);
        attributes.setPayment(payment); // jpa db relational wiring
        entityManager.persist(payment);
        entityManager.flush();

        //when
        Payment paymentReturned = paymentRepository.findOne(payment.getId());

        //then
        assertThat(paymentReturned).isEqualTo(payment);
    }

    @Test
    public void whenCreateOne() {
        //given
        Attributes attributes =
                getPaymentAttributesForTest("testPaymentId", "31926819",
                        "GB29XABC10161234567801", 1.00, 3.00,
                        "56781234");
        UUID organisationId = UUID.randomUUID();
        Payment payment = new Payment(version, organisationId, attributes);
        attributes.setPayment(payment); // jpa db relational wiring

        //when
        Payment paymentReturned = paymentRepository.save(payment);

        //then
        assertThat(paymentReturned).isEqualTo(payment);
    }

    @Test
    public void whenUpdateOne() {
        //given
        Attributes attributes =
                getPaymentAttributesForTest("testPaymentId", "31926819",
                        "GB29XABC10161234567801", 1.00, 3.00,
                        "56781234");
        UUID organisationId = UUID.randomUUID();
        Payment payment = new Payment(version, organisationId, attributes);
        attributes.setPayment(payment); // jpa db relational wiring
        entityManager.persist(payment);
        entityManager.flush();

        //when
        Payment paymentReturned = paymentRepository.findOne(payment.getId());
        assertThat(paymentReturned).isEqualTo(payment);

        UUID newOrganisationId = UUID.randomUUID();
        paymentReturned.setOrganisationId(newOrganisationId);

        Payment updatedPayment = paymentRepository.save(paymentReturned);

        //then
        assertThat(updatedPayment.getOrganisationId()).isEqualTo(newOrganisationId);
    }

    @Test
    public void whenDeleteOne() {
        //given
        Attributes attributes = getPaymentAttributesForTest("testPaymentId",
                "31926819", "GB29XABC10161234567801",
                1.00, 3.00,"56781234");
        UUID organisationId = UUID.randomUUID();
        Payment payment = new Payment(version, organisationId, attributes);
        attributes.setPayment(payment); // jpa db relational wiring
        entityManager.persist(payment);
        entityManager.flush();

        Attributes secondAttributes = getPaymentAttributesForTest("secondTestPaymentId",
                "31926800", "secondGB29XABC10161234567801",
                2.00, 4.00,"56781200");
        UUID secondOrganisationId = UUID.randomUUID();
        Payment secondPayment = new Payment(version, secondOrganisationId, secondAttributes);
        secondAttributes.setPayment(secondPayment); // jpa db relational wiring
        entityManager.persist(secondPayment);
        entityManager.flush();

        //when
        paymentRepository.delete(payment.getId());
        List<Payment> payments = paymentRepository.findAll();

        //then
        assertThat(payments.size()).isEqualTo(1);
        assertThat(payments.get(0).getId()).isEqualTo(secondPayment.getId());
    }

}