package com.example.ReactData.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReactData.model.CartItem;
import com.example.ReactData.model.Order;
import com.example.ReactData.model.Payment;
import com.example.ReactData.repo.CartRepo;
import com.example.ReactData.repo.OrderRepo;
import com.example.ReactData.repo.PaymentRepo;
import com.example.ReactData.service.PaymentService;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

	@Autowired
    public PaymentService paymentService;

	@Autowired
    private PaymentRepo paymentRepo;
	
	@Autowired
    private OrderRepo orderRepo;
	
	@Autowired
    private CartRepo cartRepo;
	
 // Step 1: Create Razorpay order
    @PostMapping("/create-order")
    public String createOrder(@RequestBody Map<String, Object> data) {
        double amount = Double.parseDouble(data.get("amount").toString());
        String currency = "INR";
        String receipt = "txn_" + System.currentTimeMillis();
        Long userId = Long.parseLong(data.get("userId").toString());

        return paymentService.createOrder(amount, currency, receipt, userId);
    }

    // Step 2: Update payment status after success
    @PostMapping("/update-status")
    public ResponseEntity<?> updatePaymentStatus(@RequestBody Map<String, Object> paymentData) {

        // Extract info from frontend
        String orderId = (String) paymentData.get("orderId");
        String paymentId = (String) paymentData.get("paymentId");
        String status = (String) paymentData.get("status");
        Double amount = Double.valueOf(paymentData.get("amount").toString());
        Long userId = paymentData.get("userId") != null
                ? Long.valueOf(paymentData.get("userId").toString())
                : null;


        // 1️. Update payment table
        Payment payment = paymentRepo.findByOrderId(orderId);
        if (payment != null) {
            payment.setPaymentId(paymentId);
            payment.setStatus(status);
            paymentRepo.save(payment);
        } else {
            return ResponseEntity.badRequest().body("Payment not found for orderId: " + orderId);
        }

        // 2️.Create a new order
        // Fetch cart items for the user (assuming you have a CartRepo)
        List<CartItem> cartItems = cartRepo.findByUserId(userId);

        // Convert product names to comma-separated string
        String productNames = cartItems.stream()
                                       .map(item -> item.getName())
                                       .collect(Collectors.joining(","));

        	Order order = new Order();
        	order.setUserId(userId);
        	order.setProductNames(productNames);
        	order.setTotalAmount(amount);
        	order.setPaymentStatus(status); // e.g., "success" or "PAID"
        	order.setOrderDate(LocalDateTime.now());

        orderRepo.save(order);

        return ResponseEntity.ok("Payment updated and order created successfully");
    }
}
