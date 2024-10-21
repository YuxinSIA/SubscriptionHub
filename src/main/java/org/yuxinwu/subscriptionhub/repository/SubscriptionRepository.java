package org.yuxinwu.subscriptionhub.repository;

import org.yuxinwu.subscriptionhub.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}

