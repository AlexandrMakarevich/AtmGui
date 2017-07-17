package com.account;

import java.util.List;

public interface AccountDao {

    void addAccount(String accountName);

    void deleteAccount(int accountId);

    List<Account> getAllAccounts();
}
