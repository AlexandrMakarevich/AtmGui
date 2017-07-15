package com.currency.listener;


import com.currency.table.CurrencyTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component("deleteCurrencyButtonListener")
public class DeleteCurrencyButtonListener implements ActionListener {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private CurrencyTable currencyTable;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int columnId = (Integer) currencyTable.getValueAt(currencyTable.getSelectedRow(), 0);
            deleteCurrency(columnId);
            currencyTable.refreshModel();
        } catch (ArrayIndexOutOfBoundsException e1) {
            JOptionPane.showMessageDialog(null, "You did not choose what to delete!");
        }
    }

    public void deleteCurrency(int currencyId) {
        String query = "delete from currency where id = :p_currency_id";
        MapSqlParameterSource namedParameter = new MapSqlParameterSource();
        namedParameter.addValue("p_currency_id", currencyId);
        namedParameterJdbcTemplate.update(query, namedParameter);
    }

    public void setCurrencyTable(CurrencyTable currencyTable) {
        this.currencyTable = currencyTable;
    }
}
