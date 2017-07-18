package com.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("accountDaoImpl")
public class AccountDaoImpl implements AccountDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void addAccount(String accountName) {
        String query = "insert into account (account_name) value (:p_account_name)";
        SqlParameterSource namedParameter = new MapSqlParameterSource("p_account_name", accountName);
        namedParameterJdbcTemplate.update(query, namedParameter);
    }

    @Override
    public void deleteAccount(int accountId) {
        String query = "delete from account where id = :p_account_id";
        SqlParameterSource namedParameter = new MapSqlParameterSource("p_account_id", accountId);
        namedParameterJdbcTemplate.update(query, namedParameter);
    }

    @Override
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

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}
