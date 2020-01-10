package org.sergei.payments.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.sergei.payments.exceptions.DataIntegrityException;
import org.sergei.payments.exceptions.DataNotFoundException;
import org.sergei.payments.exceptions.ResourceNotFoundException;
import org.sergei.payments.jpa.model.PaymentStatus;
import org.sergei.payments.jpa.model.PaymentSummary;
import org.sergei.payments.jpa.model.TypeEntity;
import org.sergei.payments.jpa.repository.PaymentRepository;
import org.sergei.payments.jpa.repository.TypeRepository;
import org.sergei.payments.rest.dto.PaymentRequestDTO;
import org.sergei.payments.rest.dto.PaymentResponseHolderDTO;
import org.sergei.payments.rest.dto.PaymentSummaryDTO;
import org.sergei.payments.rest.dto.ResponseDTO;
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
    public ResponseDTO<PaymentSummaryDTO> getPaymentByNumber(String paymentNumber) {
        var paymentSummary = paymentRepository.findPaymentByNumber(paymentNumber);
        if (paymentSummary.isPresent()) {
            PaymentSummary paymentPrinc = paymentSummary.get();
            return new ResponseDTO<>(ImmutableList.of(),
                    ImmutableList.of(
                            PaymentSummaryDTO.builder()
                                    .paymentNumber(paymentPrinc.getPaymentNumber())
                                    .cancellationFee(paymentPrinc.getCancellationFee())
                                    .build()));
        } else {
            throw new DataNotFoundException("PaymentNotFound", "PAY_001");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseDTO<PaymentResponseHolderDTO> getIdsOfAllActiveAndFilterByAmount(BigDecimal amountFrom, BigDecimal amountTo) {
        var paymentNumbers = paymentRepository.findIdsOfAllActiveAndFilterByAmount(amountFrom, amountTo);
        return new ResponseDTO<>(ImmutableList.of(),
                ImmutableList.of(PaymentResponseHolderDTO.builder()
                        .paymentNumbers(!paymentNumbers.isEmpty() ? paymentNumbers : null)
                        .build()
                ));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseDTO<PaymentResponseHolderDTO> initPayment(PaymentRequestDTO request) {
        var paymentNumber = UUID.randomUUID().toString();
        var type = typeRepository.findTypeByNumber(request.getTypeNumber());
        if (type.isPresent()) {
            TypeEntity typeEntity = type.get();
            paymentRepository.save(PaymentSummary.builder()
                    .paymentNumber(paymentNumber)
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .cardNumber(request.getCardNumber())
                    .status(PaymentStatus.ISSUED)
                    .creationDate(LocalDateTime.now())
                    .totalAmount(typeEntity.getAmount())
                    .type(typeEntity)
                    .build());
            return new ResponseDTO<>(ImmutableList.of(), ImmutableList.of());
        } else {
            throw new ResourceNotFoundException("TypeNotFound", "TPE_001");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseDTO<PaymentSummaryDTO> cancelPayment(String paymentNumber) {
        var paymentSummary = paymentRepository.findPaymentByNumber(paymentNumber);
        if (paymentSummary.isPresent()) {
            var paymentEntity = paymentSummary.get();
            if (LocalDateTime.now().isBefore(paymentEntity.getCreationDate().toLocalDate().atStartOfDay().plusHours(12))) {
                paymentEntity.setStatus(PaymentStatus.CANCELLED);
                paymentEntity.setCancellationFee(paymentEntity.getCancellationFee());
                return new ResponseDTO<>(ImmutableList.of(), ImmutableList.of());
            } else {
                throw new DataIntegrityException("PaymentCancellationException", "PAY_002");
            }
        } else {
            throw new ResourceNotFoundException("TypeNotFound", "TPE_001");
        }
    }
}
