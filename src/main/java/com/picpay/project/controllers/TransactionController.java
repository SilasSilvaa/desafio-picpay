package com.picpay.project.controllers;

import com.picpay.project.domain.transaction.Transaction;
import com.picpay.project.dtos.TransactionDTO;
import com.picpay.project.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception{
        Transaction transaction = this.transactionService.createTransaction(transactionDTO);

        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }
}
