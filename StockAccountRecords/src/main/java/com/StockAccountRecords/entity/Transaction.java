package com.StockAccountRecords.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "deposit", nullable = false)
    private Integer deposit;

    @Column(name = "spend", nullable = false)
    private Integer spend;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "balance", nullable = false)
    private Integer balance;

    @Column(name = "remark", nullable = false)
    private String remark;

    public Transaction() {
    }

    public Transaction(String username, Integer deposit, Integer spend, String date, Integer balance, String remark) {
        this.username = username;
        this.deposit = deposit;
        this.spend = spend;
        this.date = date;
        this.balance = balance;
        this.remark = remark;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSpend() {
        return spend;
    }

    public void setSpend(Integer spend) {
        this.spend = spend;
    }
}