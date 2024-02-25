package com.StockAccountRecords.service;

import com.StockAccountRecords.entity.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactionsByUsername(String username);

    void saveTransaction(Transaction transaction);
}