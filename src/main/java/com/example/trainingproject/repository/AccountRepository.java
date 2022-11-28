package com.example.trainingproject.repository;

import com.example.trainingproject.dto.AccountResponseDto;
import com.example.trainingproject.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByAccountNumber(String accountNumber);


    Optional<Account> findAccountById(Long id);

    List<Account> findAccountByCustomerId(Long id);

 /* @Query("update Account ac set ac.accountType=?1 where ac.id=?2")
    void updateAccountTypes(String type, Long id);
    */

    @Query("delete from Account ac where ac.id=?1")
    void deleteAccount(Long id);

    @Query("select new com.example.trainingproject.dto.AccountResponseDto(ac.accountNumber,ac.accountType,ac.customer.id) " +
            "from Account ac")
    List<AccountResponseDto> findAccounts();

}




