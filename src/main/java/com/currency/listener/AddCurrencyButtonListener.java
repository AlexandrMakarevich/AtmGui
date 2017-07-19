package com.currency.listener;

import com.currency.CurrencyDao;
import com.currency.table.CurrencyTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component("addCurrencyButtonListener")
public class AddCurrencyButtonListener implements ActionListener {

    private JTextField input;
    private CurrencyTable currencyTable;
    private CurrencyDao currencyDao;

    @Override
    public void actionPerformed(ActionEvent e) {
       try{
           currencyDao.insertCurrency(currencyDao.validateAndGet(input));
           currencyTable.refreshModel();
       }catch(IllegalArgumentException e1) {
           JOptionPane.showMessageDialog(null, e1.getMessage());
       }
       catch (Exception e1) {
           String formattedString = String.format("Currency %s can not be created.May by it exist", input.getText());
           JOptionPane.showMessageDialog(null, formattedString);
       }
    }

    @Autowired
    public void setCurrencyDao(CurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }

    public void setCurrencyTable(CurrencyTable currencyTable) {
        this.currencyTable = currencyTable;
    }

    public void setInput(JTextField input) {
        this.input = input;
    }
}
