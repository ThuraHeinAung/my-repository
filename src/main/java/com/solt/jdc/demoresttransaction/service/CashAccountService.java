package com.solt.jdc.demoresttransaction.service;

import com.solt.jdc.demoresttransaction.domain.CashAccount;

public interface CashAccountService {
    int getBalance(int id);

    CashAccount createAccount(CashAccount cashAccount);

    int depositCashAccount(int amount, int id);

    int withdrawCashAccount(int amount, int id);

    int transferAmountCashAccount(int sourceId, int targetId, int amount);
}
