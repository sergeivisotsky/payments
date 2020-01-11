package org.sergei.payments.jpa.repository;

import java.util.Optional;

import org.sergei.payments.jpa.model.CancellationCoefficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Sergei Visotsky
 */
@Repository
public interface CancellationCoefficientRepository extends JpaRepository<CancellationCoefficient, Long> {

    /**
     * Find payment cancellation coefficient for specific type
     *
     * @param dType type Class name as a type of type
     * @return Cancellation coefficient entity
     */
    @Query("SELECT cc FROM CancellationCoefficient cc WHERE cc.typeDType = :dType")
    Optional<CancellationCoefficient> findCoefficientByDataType(@Param("dType") String dType);
}
