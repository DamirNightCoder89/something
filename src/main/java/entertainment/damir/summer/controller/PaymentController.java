package entertainment.damir.summer.controller;

import entertainment_damir_summer.api.PaymentsApi;
import entertainment_damir_summer.model.Payment;
import entertainment_damir_summer.model.PaymentCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class PaymentController implements PaymentsApi {


    @Override
    public ResponseEntity<Payment> createPayment(PaymentCreateRequest paymentCreateRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deletePayment(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<Payment> getPaymentById(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Payment>> getPayments() {
        return null;
    }
}
