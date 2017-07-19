package com.currency;

import com.currency.table.CurrencyTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import static com.currency.CurrencyDaoImpl.CURRENCY_DAO_IMPL;

@Component(CURRENCY_DAO_IMPL)
public class CurrencyDaoImpl implements CurrencyDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public static final String CURRENCY_DAO_IMPL = "currencyDaoImpl";

    @Override
    public List<Currency> getAllCurrency() {
        String query = "select * from currency";
        List<Currency> currencyList = namedParameterJdbcTemplate.query(query, new RowMapper<Currency>() {
            @Override
            public Currency mapRow(ResultSet resultSet, int i) throws SQLException {
                Currency currency = new Currency();
                currency.setCurrencyName(resultSet.getString(2));
                currency.setId(resultSet.getInt(1));
                return currency;
            }
        });
        return currencyList;
    }

    @Override
    public int insertCurrency(String currencyName) {
        String query = "insert into currency (currency_name) value(:p_currency_name)";
        SqlParameterSource namedParameters = new MapSqlParameterSource("p_currency_name", currencyName);
        int column = namedParameterJdbcTemplate.update(query, namedParameters);
        if (column == 0) {
            System.out.println("No column was changed!");
            throw new IllegalStateException("No column was changed!");
        }
        return column;
    }

    @Override
    public void deleteCurrency(int currencyId) {
        String query = "delete from currency where id = :p_currency_id";
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        namedParameter.addValue("p_currency_id", currencyId);
        namedParameterJdbcTemplate.update(query, namedParameter);
    }

    @Override
    public int validateAndCreate(CurrencyTable currencyTable) {
        if (currencyTable.getValueAt(currencyTable.getSelectedRow(), 0) == null) {
            throw new IllegalArgumentException("Column is empty!");
        }
        return (Integer) currencyTable.getValueAt(currencyTable.getSelectedRow(), 0);
    }

    @Override
    public String validateAndGet(JTextField input) {
        if (input.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("You don't enter account name!");
        }
        return input.getText();
    }

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}
