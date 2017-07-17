package com.account.listener;

import com.account.AccountDaoImpl;
import com.account.table.AccountTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component("deleteAccountButtonListener")
public class DeleteAccountButtonListener implements ActionListener {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private AccountTable accountTable;
    private AccountDaoImpl accountDaoImpl = new AccountDaoImpl();


    @Override
    public void actionPerformed(ActionEvent e) {
        accountDaoImpl.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
        try {
            int accountId = validate();
            accountDaoImpl.deleteAccount(accountId);
            accountTable.refreshModel();
        } catch (ArrayIndexOutOfBoundsException e1) {
            JOptionPane.showMessageDialog(null, "You did not choose what to delete!");
        }
    }

    public int validate() {
        if (accountTable.getValueAt(accountTable.getSelectedRow(), 0) == null) {
            throw new IllegalArgumentException("Column not initialized!");
        }
        return (Integer) accountTable.getValueAt(accountTable.getSelectedRow(), 0);
    }

    public void deleteAccount(int accountId) {
        String query = "delete from account where id = :p_account_id";
        SqlParameterSource namedParameter = new MapSqlParameterSource("p_account_id", accountId);
        namedParameterJdbcTemplate.update(query, namedParameter);
    }

    public void setAccountTable(AccountTable accountTable) {
        this.accountTable = accountTable;
    }
}
