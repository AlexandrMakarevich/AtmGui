package com.currency;

import java.util.List;

public interface CurrencyDao {

    List<Currency> getAllCurrency();

    int insertCurrency(String currencyName);

    void deleteCurrency(int currencyId);

    Integer validateAndCreate(Integer currencyId);

    String validateAndGet(String input);
}
