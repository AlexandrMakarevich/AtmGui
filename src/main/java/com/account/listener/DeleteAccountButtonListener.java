package com.account.listener;

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


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int accountId = (Integer) accountTable.getValueAt(accountTable.getSelectedRow(), 0);
            deleteAccount(accountId);
            accountTable.refreshModel();
        } catch (ArrayIndexOutOfBoundsException e1) {
            JOptionPane.showMessageDialog(null, "You did not choose what to delete!");
        }
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
