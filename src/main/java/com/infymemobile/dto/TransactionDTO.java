package com.infymemobile.dto;


import java.time.LocalDateTime;

import com.infymemobile.entity.TransactionEntity;


public class TransactionDTO {

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

    public TransactionEntity prepareEntity(TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setModeOfTransaction(transactionDTO.getModeOfTransaction());
        transactionEntity.setPaidTo(transactionDTO.getPaidTo());
        transactionEntity.setReceiverAccountNumber(transactionDTO.getReceiverAccountNumber());
        transactionEntity.setAmount(transactionDTO.getAmount());
        transactionEntity.setTransactionDateTime(transactionDTO.getTransactionDateTime());
        transactionEntity.setRemarks(transactionDTO.getRemarks());
        transactionEntity.setPaidFrom(transactionDTO.getPaidFrom());
        transactionEntity.setSenderAccountNumber(transactionDTO.getSenderAccountNumber());
        return transactionEntity;

    }

    public TransactionDTO prepareDTO(TransactionEntity transactionEntity) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionId(transactionEntity.getTransactionId());
        transactionDTO.setModeOfTransaction(transactionEntity.getModeOfTransaction());
        transactionDTO.setPaidTo(transactionEntity.getPaidTo());
        transactionDTO.setReceiverAccountNumber(transactionEntity.getReceiverAccountNumber());
        transactionDTO.setAmount(transactionEntity.getAmount());
        transactionDTO.setTransactionDateTime(transactionEntity.getTransactionDateTime());
        transactionDTO.setRemarks(transactionEntity.getRemarks());
        transactionDTO.setPaidFrom(transactionEntity.getPaidFrom());
        transactionDTO.setSenderAccountNumber(transactionEntity.getSenderAccountNumber());
        return transactionDTO;

    }
    @Override
    public String toString() {
        return "TransactionDTO [modeOfTransaction=" + modeOfTransaction + ", paidTo=" + paidTo
                + ", receiverAccountNumber=" + receiverAccountNumber + ", amount=" + amount + ", remarks=" + remarks
                + ", paidFrom=" + paidFrom + ", senderAccountNumber=" + senderAccountNumber + "]";
    }



}
