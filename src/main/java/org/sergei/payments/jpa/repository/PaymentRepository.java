package org.sergei.payments.jpa.repository;

import java.math.BigDecimal;
import java.util.List;

import org.sergei.payments.jpa.model.PaymentSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Sergei Visotsky
 */
@Repository
public interface PaymentRepository extends JpaRepository<PaymentSummary, Long> {

    @Query("SELECT p.paymentNumber FROM PaymentSummary p " +
            " WHERE p.status <> 'CANCELLED' " +
            " AND p.totalAmount BETWEEN :amountFrom AND :amountTo")
    List<String> findIdsOfAllActiveAndFilterByAmount(@Param("amountFrom") BigDecimal amountFrom,
                                                     @Param("amountTo") BigDecimal amountTo);
}
