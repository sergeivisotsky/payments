package org.sergei.payments.service.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.sergei.payments.jpa.repository.ResponseMessageRepository;
import org.sergei.payments.rest.dto.ResponseErrorDTO;
import org.sergei.payments.service.ResponseMessageService;
import org.springframework.stereotype.Service;

/**
 * @author Sergei Visotsky
 */
@Service
@RequiredArgsConstructor
public class ResponseMessageServiceImpl implements ResponseMessageService {

    private final ResponseMessageRepository responseMessageRepository;

    @Override
    public List<ResponseErrorDTO> responseErrorListByCode(String code) {
        var responseMessageList = responseMessageRepository.findResponseMessageByCode(code);
        var responseErrorList = new ArrayList<ResponseErrorDTO>();
        responseMessageList.forEach(message ->
                responseErrorList.add(new ResponseErrorDTO(message.getCode(), message.getMessage(), "ERROR")));
        return responseErrorList;
    }

}
