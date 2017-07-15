package com.currency.listener;

import com.currency.table.CurrencyTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component("addCurrencyButtonListener")
public class AddCurrencyButtonListener implements ActionListener {

    private JTextField input;
    private CurrencyTable currencyTable;

    @Autowired

    private NamedParameterJdbcTemplate namedParameter;

    @Override
    public void actionPerformed(ActionEvent e) {
       try{
           insertCurrency(input.getText());
           currencyTable.refreshModel();
       }
       catch (Exception e1) {
           JOptionPane.showMessageDialog(null, e1.getMessage());
       }
    }

    public int insertCurrency(String currencyName) {
        String query = "insert into currency (currency_name) value(:p_currency_name)";
        SqlParameterSource namedParameters = new MapSqlParameterSource("p_currency_name", currencyName);
        int column = namedParameter.update(query, namedParameters);
        if (column == 0) {
            System.out.println("No column was changed!");
            throw new IllegalStateException("No column was changed!");
        }
        return column;
    }

    public void setCurrencyTable(CurrencyTable currencyTable) {
        this.currencyTable = currencyTable;
    }

    public void setInput(JTextField input) {
        this.input = input;
    }
}
