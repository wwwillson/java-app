package com.StockAccountRecords.controller;

import com.StockAccountRecords.entity.Transaction;
import com.StockAccountRecords.model.TransactionDto;
import com.StockAccountRecords.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/addTransaction")
    public ResponseEntity<String> addTransaction(@RequestBody TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setUsername(transactionDto.getUsername());
        transaction.setDate(transactionDto.getDate());
        transaction.setDeposit(transactionDto.getDeposit());
        transaction.setSpend(transactionDto.getSpend());
        transaction.setBalance(transactionDto.getBalance());
        transaction.setRemark(transactionDto.getRemark());
        if (transaction.getSpend() == null) {
            transaction.setSpend(0);
        }
        if (transaction.getDeposit() == null) {
            transaction.setDeposit(0);
        }

        transactionService.saveTransaction(transaction);

        return new ResponseEntity<>("Transaction added successfully", HttpStatus.OK);
    }
}
