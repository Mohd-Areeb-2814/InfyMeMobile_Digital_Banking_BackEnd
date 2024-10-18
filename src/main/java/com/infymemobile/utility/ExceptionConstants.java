package com.infymemobile.utility;

public enum ExceptionConstants {
    SERVER_ERROR("server.error"),
    AUTHENTICATION_FAILED("authentication.failed"),
    USER_ALREADY_PRESENT("user.already.present"),
    USER_NOT_FOUND("user.not.found"),
    USERID_NOT_FOUND("user.id.not.found"),
    NO_USERS_FOUND("no.users.found"),
    NO_ACCOUNTS_FOUND("no.account.found"),
    NO_ACCOUNT_IS_LINKED("no.account.is.linked"),
    ACCOUNT_ALREADY_LINKED("account.already.linked"),
    INSUFFICIENT_FUNDS("insufficient.funds"),
    NO_ACTIVE_TRANSACTIONS("no.active.transactions"),
    OTP_DOESNOT_MATCH("otp.doesnot.match");

    private final String type;

    private ExceptionConstants(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return this.type;
    }
}

