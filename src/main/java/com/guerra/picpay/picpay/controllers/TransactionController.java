package com.guerra.picpay.picpay.controllers;

import com.guerra.picpay.picpay.DTO.TransactionDTO;
import com.guerra.picpay.picpay.models.Transactions;
import com.guerra.picpay.picpay.services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionServices transactionServices;

    @PostMapping
    public ResponseEntity<Transactions> createTransaction(@RequestBody TransactionDTO transaction) throws Exception {
        Transactions transactions = this.transactionServices.transaction(transaction);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping("/devolution")
    public ResponseEntity<Transactions> devolution(@RequestBody TransactionDTO transaction) throws Exception {
        Transactions transactions = this.transactionServices.transaction(transaction);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }


}
