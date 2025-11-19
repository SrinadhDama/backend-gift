package com.example.ReactData.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.ReactData.model.Payment;
import com.example.ReactData.repo.PaymentRepo;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class PaymentService {

    @Autowired
    PaymentRepo paymentRepository;

    // Inject Razorpay credentials from application.properties
    @Value("${razorpay.key.id}")
    private String razorpayKeyId;

    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;

    // Create Razorpay order
    public String createOrder(double amount, String currency, String receipt, long userId) {
        try {
            RazorpayClient client = new RazorpayClient(razorpayKeyId, razorpayKeySecret);

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", (int)(amount * 100));
            orderRequest.put("currency", currency);
            orderRequest.put("receipt", receipt);

            Order order = client.orders.create(orderRequest);

            Payment payment = new Payment();
            payment.setUserId(userId);
            payment.setAmount(amount);
            payment.setCurrency(currency);
            payment.setReceipt(receipt);
            payment.setOrderId(order.get("id"));
            payment.setStatus("CREATED");

            paymentRepository.save(payment);

            return order.toString();

        } catch (Exception e) {
            throw new RuntimeException("Error creating Razorpay order: " + e.getMessage());
        }
    }

    public void updatePaymentStatus(String orderId, String paymentId, String status) {
        Payment payment = paymentRepository.findByOrderId(orderId);
        if (payment != null) {
            payment.setPaymentId(paymentId);
            payment.setStatus(status);
            paymentRepository.save(payment);
        }
    }
}
