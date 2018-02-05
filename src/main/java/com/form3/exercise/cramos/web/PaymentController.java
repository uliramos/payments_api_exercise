package com.form3.exercise.cramos.web;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.form3.exercise.cramos.model.Payment;
import com.form3.exercise.cramos.service.PaymentService;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody APIResponse<List<Payment>> getAll() {
        return new APIResponse<>(paymentService.getAll());
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody APIResponse<Payment> getOne(@PathVariable("id") UUID id) {
        return new APIResponse<>(paymentService.getOne(id));
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody APIResponse<Payment> create(Payment payment) {
        return new APIResponse<>(paymentService.create(payment));
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody APIResponse<Payment> create(@PathVariable("id") UUID id, Payment payment) {
        payment.setId(id);
        return new APIResponse<>(paymentService.create(payment));
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void create(@PathVariable("id") UUID id) {
        paymentService.remove(id);
    }

}
