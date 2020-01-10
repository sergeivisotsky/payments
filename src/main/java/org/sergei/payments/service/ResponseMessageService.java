package org.sergei.payments.service;

import java.util.List;

import org.sergei.payments.rest.dto.ResponseErrorDTO;

/**
 * @author Sergei Visotsky
 */
public interface ResponseMessageService {

    /**
     * Get response message list by code
     *
     * @param code response message code
     * @return collection with all the errors
     */
    List<ResponseErrorDTO> responseErrorListByCode(String code);

}
