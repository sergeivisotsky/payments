/*
 * Copyright © 2018-2020 Sergei Visotsky as an original author.
 * No portion of this work may be copied, distributed, modified, or incorporated
 * into any other media without Sergei Visotsky’s prior written consent.
 */

package org.sergei.payments.jpa.repository;

import java.util.List;

import org.sergei.payments.jpa.model.ResponseMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Sergei Visotsky
 */
@Repository
public interface ResponseMessageRepository extends JpaRepository<ResponseMessage, Long> {

    /**
     * Select response message by code
     *
     * @param code message code
     * @return Response message wrapped into the collection
     */
    @Query("SELECT rm FROM ResponseMessage rm WHERE rm.code = :code")
    List<ResponseMessage> findResponseMessageByCode(@Param("code") String code);
}
