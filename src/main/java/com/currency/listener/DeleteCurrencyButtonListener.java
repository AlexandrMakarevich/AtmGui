package com.currency.listener;

import com.currency.CurrencyDao;
import com.currency.table.CurrencyTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component("deleteCurrencyButtonListener")
public class DeleteCurrencyButtonListener implements ActionListener {

    private CurrencyDao currencyDao;
    private CurrencyTable currencyTable;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int currencyId = currencyDao.validateAndCreate(currencyTable);
            currencyDao.deleteCurrency(currencyId);
            currencyTable.refreshModel();
        } catch (ArrayIndexOutOfBoundsException e1) {
            JOptionPane.showMessageDialog(null, "You did not choose what to delete!");
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
    }

    @Autowired
    public void setCurrencyDao(CurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }

    public void setCurrencyTable(CurrencyTable currencyTable) {
        this.currencyTable = currencyTable;
    }
}
