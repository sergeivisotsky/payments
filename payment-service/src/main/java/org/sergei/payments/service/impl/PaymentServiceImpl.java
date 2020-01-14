package org.sergei.payments.service.impl;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import org.sergei.payments.exceptions.DataIntegrityException;
import org.sergei.payments.exceptions.DataNotFoundException;
import org.sergei.payments.exceptions.ResourceNotFoundException;
import org.sergei.payments.jpa.model.CancellationCoefficient;
import org.sergei.payments.jpa.model.PaymentStatus;
import org.sergei.payments.jpa.model.PaymentSummary;
import org.sergei.payments.jpa.model.PaymentTransferLog;
import org.sergei.payments.jpa.model.TypeEntity;
import org.sergei.payments.jpa.repository.CancellationCoefficientRepository;
import org.sergei.payments.jpa.repository.PaymentRepository;
import org.sergei.payments.jpa.repository.PaymentTransferLogRepository;
import org.sergei.payments.jpa.repository.TypeRepository;
import org.sergei.payments.rest.dto.PaymentRequestDTO;
import org.sergei.payments.rest.dto.PaymentResponseHolderDTO;
import org.sergei.payments.rest.dto.PaymentSummaryDTO;
import org.sergei.payments.rest.dto.ResponseDTO;
import org.sergei.payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sergei Visotsky
 */
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    @Value("${listener.url}")
    private String listenerUrl;
    private final PaymentTransferLogRepository paymentTransferLogRepository;
    private final CancellationCoefficientRepository coefficientRepository;
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
            PaymentSummary savedPayment = paymentRepository.save(PaymentSummary.builder()
                    .paymentNumber(paymentNumber)
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .cardNumber(request.getCardNumber())
                    .status(PaymentStatus.ISSUED)
                    .creationDate(LocalDateTime.now())
                    .totalAmount(typeEntity.getAmount())
                    .type(typeEntity)
                    .build());

            // Send payment ID to the listener service
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(listenerUrl + "/" +
                    savedPayment.getPaymentNumber(), HttpMethod.GET, null, String.class);
            paymentTransferLogRepository.save(
                    PaymentTransferLog.builder()
                            .paymentNumber(savedPayment.getPaymentNumber())
                            .httpStatusCode(response.getStatusCode().value())
                            .httpComment(response.getStatusCode().getReasonPhrase())
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
            // Calculates time diff
            LocalDateTime currTime = LocalDateTime.now();
            LocalDateTime creationDate = paymentEntity.getCreationDate();
            long diff = Duration.between(currTime, creationDate).abs().toHours();
            if (currTime.isBefore(LocalDateTime.of(creationDate.toLocalDate().plusDays(1), LocalTime.MIDNIGHT))) {
                // Query for cancellation coefficient
                Optional<CancellationCoefficient> coefficient =
                        coefficientRepository.findCoefficientByDataType(paymentEntity.getType().getDtype());
                paymentEntity.setStatus(PaymentStatus.CANCELLED);
                // Calculate cancellation fee
                paymentEntity.setCancellationFee(BigDecimal.valueOf(diff * Double.parseDouble(String.valueOf(coefficient.get().getCoefficient()))));
                paymentRepository.save(paymentEntity);
                return new ResponseDTO<>(ImmutableList.of(), ImmutableList.of());
            } else {
                throw new DataIntegrityException("PaymentCancellationException", "PAY_002");
            }
        } else {
            throw new ResourceNotFoundException("TypeNotFound", "TPE_001");
        }
    }
}
