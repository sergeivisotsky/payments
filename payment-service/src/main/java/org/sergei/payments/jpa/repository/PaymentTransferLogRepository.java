package org.sergei.payments.jpa.repository;

import org.sergei.payments.jpa.model.PaymentTransferLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sergei Visotsky
 */
@Repository
public interface PaymentTransferLogRepository extends JpaRepository<PaymentTransferLog, Long> {
}
