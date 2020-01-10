package org.sergei.payments.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.sergei.payments.jpa.model.PaymentStatus;
import org.sergei.payments.jpa.model.PaymentSummary;
import org.sergei.payments.jpa.model.TypeEntity;
import org.sergei.payments.jpa.repository.PaymentRepository;
import org.sergei.payments.jpa.repository.TypeRepository;
import org.sergei.payments.rest.controller.dto.request.PaymentRequestDTO;
import org.sergei.payments.rest.controller.dto.response.PaymentResponseHolderDTO;
import org.sergei.payments.rest.controller.dto.response.PaymentSummaryDTO;
import org.sergei.payments.rest.controller.dto.response.ResponseDTO;
import org.sergei.payments.service.PaymentService;
import org.springframework.stereotype.Service;

/**
 * @author Sergei Visotsky
 */
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final TypeRepository typeRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseDTO<PaymentResponseHolderDTO> getIdsOfAllActiveAndFilterByAmount(BigDecimal amountFrom, BigDecimal amountTo) {
        return new ResponseDTO<>(ImmutableList.of(),
                ImmutableList.of(PaymentResponseHolderDTO.builder()
                        .paymentNumbers(paymentRepository.findIdsOfAllActiveAndFilterByAmount(amountFrom, amountTo))
                        .build()
                ));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseDTO<PaymentSummaryDTO> getPaymentByNumber(String paymentNumber) {
        // TODO
        throw new NotImplementedException("Method is not implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseDTO<PaymentResponseHolderDTO> initPayment(PaymentRequestDTO request) {
        String paymentNumber = UUID.randomUUID().toString();
        Optional<TypeEntity> type = typeRepository.findTypeByNumber(request.getTypeNumber());
//        String typeOfType = request.getTypeOfType();
//        TypeEntity typeEntity = null;
//        switch (typeOfType) {
//            case "TypeOneEntity": {
//                Optional<TypeOneEntity> type = typeOneRepository.findTypeByNumber(request.getTypeNumber());
//                if (type.isPresent()) {
//                    typeEntity = type.get();
//                } else {
//                    throw new DataNotFoundException("TypeNotFound", "TPE_001");
//                }
//                break;
//            }
//            case "TypeTwoEntity": {
//                Optional<TypeTwoEntity> type = typeTwoRepository.findTypeByNumber(request.getTypeNumber());
//                if (type.isPresent()) {
//                    typeEntity = type.get();
//                } else {
//                    throw new DataNotFoundException("TypeNotFound", "TPE_001");
//                }
//                break;
//            }
//            case "TypeThreeEntity": {
//                Optional<TypeThreeEntity> type = typeThreeRepository.findTypeByNumber(request.getTypeNumber());
//                if (type.isPresent()) {
//                    typeEntity = type.get();
//                } else {
//                    throw new DataNotFoundException("TypeNotFound", "TPE_001");
//                }
//                break;
//            }
//        }
        paymentRepository.save(PaymentSummary.builder()
                .paymentNumber(paymentNumber)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .cardNumber(request.getCardNumber())
                .status(PaymentStatus.ISSUED)
                .creationDate(LocalDateTime.now())
                .totalAmount(type.get().getAmount())
                .type(type.get())
                .build());
        return new ResponseDTO<>(ImmutableList.of(), ImmutableList.of());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseDTO<PaymentSummaryDTO> cancelPayment(String paymentNumber) {
        // TODO
        throw new NotImplementedException("Method is not implemented");
    }
}
