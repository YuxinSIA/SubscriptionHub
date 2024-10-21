package org.yuxinwu.subscriptionhub.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yuxinwu.subscriptionhub.model.Subscription;
import org.yuxinwu.subscriptionhub.repository.SubscriptionRepository;

import java.util.List;

@Service
public class SubscriptionService {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionService.class);

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<Subscription> getAllSubscriptions() {
        logger.debug("Fetching all subscriptions");
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        logger.info("Found {} subscriptions", subscriptions.size());
        return subscriptions;
    }

    public Subscription saveSubscription(Subscription subscription) {
        logger.debug("Saving subscription: {}", subscription.getName());
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        logger.info("Subscription saved successfully: {}", savedSubscription.getName());
        return savedSubscription;
    }
}
