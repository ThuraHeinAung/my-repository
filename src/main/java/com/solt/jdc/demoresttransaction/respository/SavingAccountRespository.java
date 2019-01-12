package com.solt.jdc.demoresttransaction.respository;

import com.solt.jdc.demoresttransaction.domain.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingAccountRespository extends JpaRepository<SavingAccount, Integer> {
}
