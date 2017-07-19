package com.currency;

import com.currency.table.CurrencyTable;

import javax.swing.*;
import java.util.List;

public interface CurrencyDao {

    List<Currency> getAllCurrency();

    int insertCurrency(String currencyName);

    void deleteCurrency(int currencyId);

    int validateAndCreate(CurrencyTable currencyTable);

    String validateAndGet(JTextField input);
}
