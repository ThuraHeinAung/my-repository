package com.solt.jdc.demoresttransaction.service;

import com.solt.jdc.demoresttransaction.domain.CashAccount;
import com.solt.jdc.demoresttransaction.respository.CashAccountRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CashAccountServieImpl implements CashAccountService {
    @Autowired
    private CashAccountRespository cashAccountRespository;

    @Override
    public int getBalance(int id) {
        return cashAccountRespository.getOne(id).getAmount();
    }

    @Override
    public CashAccount createAccount(CashAccount cashAccount) {

        return cashAccountRespository.save(cashAccount);
    }

    @Override
    public int depositCashAccount(int amount, int id) {
        int totalAmount = getBalance(id) + amount;
        CashAccount cashAccount = cashAccountRespository.getOne(id);
        cashAccount.setAmount(totalAmount);
        cashAccountRespository.saveAndFlush(cashAccount);
        return cashAccount.getAmount();
    }

    @Override
    public int withdrawCashAccount(int amount, int id) {
        int totalAmount = getBalance(id) - amount;
        CashAccount cashAccount = cashAccountRespository.getOne(id);
        cashAccount.setAmount(totalAmount);
        cashAccountRespository.saveAndFlush(cashAccount);
        return cashAccount.getAmount();
    }

    @Transactional
    public int transferAmountCashAccount(int sourceId, int targetId, int amount) {
        CashAccount sourceAccount = cashAccountRespository.getOne(sourceId);
        sourceAccount.setAmount(getBalance(sourceId) - amount);
        CashAccount targetAccount = cashAccountRespository.getOne(targetId);
        targetAccount.setAmount(getBalance(targetId) + amount);
        return getBalance(targetId);
    }
}
