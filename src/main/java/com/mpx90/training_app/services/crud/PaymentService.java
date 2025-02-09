package com.mpx90.training_app.services.crud;

import com.mpx90.training_app.dto.core.Payment;
import com.mpx90.training_app.mappers.PaymentMapper;
import com.mpx90.training_app.models.payment.PaymentEntity;
import com.mpx90.training_app.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    public Payment create(Payment payment) {
        PaymentEntity entity = paymentMapper.toEntity(payment);
        return paymentMapper.toDto(paymentRepository.save(entity));
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll().stream()
                .map(paymentMapper::toDto)
                .collect(Collectors.toList());
    }

    public Payment findById(Long id) {
        return paymentRepository.findById(id)
                .map(paymentMapper::toDto)
                .orElse(null);
    }

    public Payment update(Long id, Payment payment) {
        return paymentRepository.findById(id)
                .map(existing -> {
                    PaymentEntity updatedEntity = paymentMapper.toEntity(payment);
                    updatedEntity.setId(existing.getId());
                    return paymentMapper.toDto(paymentRepository.save(updatedEntity));
                })
                .orElse(null);
    }

    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }
}
