package org.sergei.payments.jpa.repository;

import org.sergei.payments.jpa.model.PaymentTransmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sergei Visotsky
 */
@Repository
public interface PaymentTransmissionRepository extends JpaRepository<PaymentTransmission, Long> {
}
