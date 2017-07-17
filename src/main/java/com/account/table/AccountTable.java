package com.account.table;

import com.account.Account;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountTable extends JTable {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private AccountTableModel accountTableModel = new AccountTableModel();

    public AccountTable(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        setModel(accountTableModel);
        refreshModel();
    }

    public List<Account> getAllAccounts() {
        String query = "select * from account";
        return namedParameterJdbcTemplate.query(query, new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                Account account = new Account();
                account.setId(rs.getInt(1));
                account.setAccountName(rs.getString(2));
                return account;
            }
        });
    }

    public void refreshModel() {
        accountTableModel.setAccountList(getAllAccounts());
        accountTableModel.fireTableDataChanged();
    }
}
