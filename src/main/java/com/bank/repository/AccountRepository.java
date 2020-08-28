package com.bank.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

	public Optional<Account> findByAccountId(String accountId);

	@Query("select account from Account account where account.balance>= :minBalance")
	public Page<Account> findAllByMinBalance(@Param("minBalance") Double minBalance, Pageable pageable);

	@Query("select account from Account account where account.age>60 and account.balance> 3000")
	public Page<Account> getAllAccountWhereAgeGreaterThan60AndIfCredited2000BalanceMoreThan5000(Pageable pageable);

}
