package com.example.finalprojectaozcann.controller;

import com.example.finalprojectaozcann.model.request.TransferCheckingAccountToDebitCardRequest;
import com.example.finalprojectaozcann.model.request.TransferToAccountRequest;
import com.example.finalprojectaozcann.model.response.SuccessAccountTransferResponse;
import com.example.finalprojectaozcann.model.response.SuccessCardTransferResponse;
import com.example.finalprojectaozcann.service.TransferService;
import com.example.finalprojectaozcann.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService transferService;
    private final Validator<TransferToAccountRequest> transferToAccountRequestValidator;
    private final Validator<TransferCheckingAccountToDebitCardRequest> transferCheckingAccountToDebitCardRequestValidator;


    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(path = "/checking/deposit")
    public ResponseEntity<SuccessAccountTransferResponse> transferCheckingToDeposit(@RequestBody TransferToAccountRequest request,
                                                                                    HttpServletRequest httpServletRequest) {
        transferToAccountRequestValidator.validate(request);
        return ResponseEntity.ok(transferService.transferCheckingToDeposit(request, httpServletRequest));
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(path = "/deposit/checking")
    public ResponseEntity<SuccessAccountTransferResponse> transferDepositToChecking(@RequestBody TransferToAccountRequest request,
                                                                                    HttpServletRequest httpServletRequest) {
        transferToAccountRequestValidator.validate(request);
        return ResponseEntity.ok(transferService.transferDepositToChecking(request, httpServletRequest));
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(path = "/checking/checking")
    public ResponseEntity<SuccessAccountTransferResponse> transferCheckingToChecking(@RequestBody TransferToAccountRequest request,
                                                                                     HttpServletRequest httpServletRequest) {
        transferToAccountRequestValidator.validate(request);
        return ResponseEntity.ok(transferService.transferCheckingToChecking(request, httpServletRequest));
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(path = "/checking/debit-card")
    public ResponseEntity<SuccessCardTransferResponse> transferCheckingToDebitCard(@RequestBody TransferCheckingAccountToDebitCardRequest request,
                                                                                   HttpServletRequest httpServletRequest) {
        transferCheckingAccountToDebitCardRequestValidator.validate(request);
        return ResponseEntity.ok(transferService.transferCheckingToDebitCard(request, httpServletRequest));
    }
//
//    @PreAuthorize("hasAuthority('USER')")
//    @PostMapping(path = "/bank-card/put-money")
//    public ResponseEntity<TransferSuccessResponse> transferCheckingToDebitCard(@RequestBody TransferToDebitCardRequest request, HttpServletRequest httpServletRequest) {
//        TransferToDebitCardRequestValidator.validate(request);
//        return ResponseEntity.ok(transferService.transferCheckingToDebitCard(request, httpServletRequest));
//    }
//


}
