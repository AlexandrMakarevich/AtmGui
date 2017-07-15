package com.account.listener;

import com.account.table.AccountTable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component("addAccountButtonListener")
public class AddAccountButtonListener implements ActionListener {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JTextField input;
    private AccountTable accountTable;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            addAccount(input.getText());
            accountTable.refreshModel();

        } catch (Exception e1) {
            String formattedString = String.format("Account %s doesn't create.May by it exist", input.getText());
            JOptionPane.showMessageDialog(null, formattedString);
        }
    }

    public void addAccount(String accountName) {
        String query = "insert into account (account_name) value (:p_account_name)";
        SqlParameterSource namedParameter = new MapSqlParameterSource("p_account_name", accountName);
        namedParameterJdbcTemplate.update(query, namedParameter);
    }

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void setInput(JTextField input) {
        this.input = input;
    }

    public void setAccountTable(AccountTable accountTable) {
        this.accountTable = accountTable;
    }
}
