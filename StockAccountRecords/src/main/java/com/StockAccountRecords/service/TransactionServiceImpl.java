package com.StockAccountRecords.service;

import com.StockAccountRecords.dao.TransactionRepository;
import com.StockAccountRecords.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// TransactionServiceImpl.java
@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionServiceImpl(TransactionRepository theTransactionRepository){
        this.transactionRepository = theTransactionRepository;
    };

    @Override
    public List<Transaction> getTransactionsByUsername(String username) {
        return this.transactionRepository.findByUsernameOrderByDate(username);
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        this.transactionRepository.save(transaction);
    }
}