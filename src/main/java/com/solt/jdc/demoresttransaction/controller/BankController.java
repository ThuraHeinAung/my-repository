package com.solt.jdc.demoresttransaction.controller;

import com.solt.jdc.demoresttransaction.domain.CashAccount;
import com.solt.jdc.demoresttransaction.service.CashAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class BankController {
    @Autowired
    private CashAccountService cashAccountService;

    @ModelAttribute(value = "cashaccount")
    public List<CashAccount> getCashAccount() {
        return Arrays.asList(new CashAccount("Thura", 2000),
                new CashAccount("Tun", 2300));
    }

    @GetMapping("/cash/cashaccounts")
    public String create(@ModelAttribute("cashaccount") List<CashAccount> list) {
        list.stream().forEach(cashAccountService::createAccount);
        return "cash account is scccessfully created";
    }

    @GetMapping("/cash/depositpayment/{amount}/{id}")
    public String depositCashAccount(@PathVariable("amount") int amount, @PathVariable("id") int id) {
        cashAccountService.depositCashAccount(amount, id);
        return id + " successfully deposited" + amount + "and now balance is" + cashAccountService.getBalance(id);
    }

    @GetMapping("/cash/withdrawpayment/{amount}/{id}")
    public String WithdrawCashAccount(@PathVariable("amount") int amount, @PathVariable("id") int id) {
        cashAccountService.withdrawCashAccount(amount, id);
        return id + " successfully withdrew" + amount + "and now balance is" + cashAccountService.getBalance(id);
    }

    @GetMapping("/cash/tranferpayment/{sourceId}/{targetId}/{amount}")
    public String transferAmountCashAccount(@PathVariable("sourceId") int source, @PathVariable("targetId") int target, @PathVariable("amount") int amount) {
        int balance = cashAccountService.transferAmountCashAccount(source, target, amount);
        return "after transfer " + target + "cash account has " + balance + " amount";
    }
}
