package com.infymemobile.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "digitalbankaccount")
public class DigitalBankAccountEntity {

    @Id
    private String digitalBankingId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mobile_number")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_number")
    private BankAccountEntity bankAccountEntity;

    private String accountType;




    public String getDigitalBankingId() {
        return digitalBankingId;
    }
    public void setDigitalBankingId(String digitalBankingId) {
        this.digitalBankingId = digitalBankingId;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public BankAccountEntity getBankAccountEntity() {
        return bankAccountEntity;
    }
    public void setBankAccountEntity(BankAccountEntity bankAccountEntity) {
        this.bankAccountEntity = bankAccountEntity;
    }

    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

}
