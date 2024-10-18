package com.infymemobile.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    private String modeOfTransaction;
    private Long paidTo;
    private Long receiverAccountNumber;
    private Double amount;
    private LocalDateTime transactionDateTime;
    private String remarks;
    private Long paidFrom;
    private Long senderAccountNumber;
    public Integer getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }
    public String getModeOfTransaction() {
        return modeOfTransaction;
    }
    public void setModeOfTransaction(String modeOfTransaction) {
        this.modeOfTransaction = modeOfTransaction;
    }
    public Long getPaidTo() {
        return paidTo;
    }
    public void setPaidTo(Long paidTo) {
        this.paidTo = paidTo;
    }
    public Long getReceiverAccountNumber() {
        return receiverAccountNumber;
    }
    public void setReceiverAccountNumber(Long receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }
    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public Long getPaidFrom() {
        return paidFrom;
    }
    public void setPaidFrom(Long paidFrom) {
        this.paidFrom = paidFrom;
    }
    public Long getSenderAccountNumber() {
        return senderAccountNumber;
    }
    public void setSenderAccountNumber(Long senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }


}
