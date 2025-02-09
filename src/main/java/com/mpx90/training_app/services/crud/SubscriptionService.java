package com.mpx90.training_app.services.crud;

import com.mpx90.training_app.dto.core.Subscription;
import com.mpx90.training_app.mappers.SubscriptionMapper;
import com.mpx90.training_app.models.payment.SubscriptionEntity;
import com.mpx90.training_app.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, SubscriptionMapper subscriptionMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionMapper = subscriptionMapper;
    }

    public Subscription create(Subscription subscription) {
        SubscriptionEntity entity = subscriptionMapper.toEntity(subscription);
        return subscriptionMapper.toDto(subscriptionRepository.save(entity));
    }

    public List<Subscription> findAll() {
        return subscriptionRepository.findAll().stream()
                .map(subscriptionMapper::toDto)
                .collect(Collectors.toList());
    }

    public Subscription findById(Long id) {
        return subscriptionRepository.findById(id)
                .map(subscriptionMapper::toDto)
                .orElse(null);
    }

    public Subscription update(Long id, Subscription subscription) {
        return subscriptionRepository.findById(id)
                .map(existing -> {
                    SubscriptionEntity updatedEntity = subscriptionMapper.toEntity(subscription);
                    updatedEntity.setId(existing.getId());
                    return subscriptionMapper.toDto(subscriptionRepository.save(updatedEntity));
                })
                .orElse(null);
    }

    public void delete(Long id) {
        subscriptionRepository.deleteById(id);
    }
}