package org.sergei.payments.jpa.repository;

import java.util.Optional;

import org.sergei.payments.jpa.model.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Sergei Visotsky
 */
@Repository
public interface TypeRepository extends JpaRepository<TypeEntity, Long> {

    @Query("SELECT t FROM TypeEntity t WHERE t.typeNumber = :typeNumber")
    Optional<TypeEntity> findTypeByNumber(@Param("typeNumber") String typeNumber);
}
