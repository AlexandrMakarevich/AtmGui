package com.currency.table;

import com.currency.Currency;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CurrencyTable extends JTable {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CurrencyTable(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        init();
        setColumnWidth();
    }

    private CurrencyTableModel currencyTableModel = new CurrencyTableModel();

    private void init() {
        List<Currency> currencyList = getAllCurrency();
        currencyTableModel.setCurrencyList(currencyList);
        setModel(currencyTableModel);
    }

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

    public void setColumnWidth() {
        setSize(60, 100);
        getColumnModel().getColumn(0).setWidth(10);
        getColumnModel().getColumn(1).setWidth(50);
    }

    public void refreshModel() {
        currencyTableModel.setCurrencyList(getAllCurrency());
        currencyTableModel.fireTableDataChanged();
    }
}
