package com.currency.table;

import com.currency.Currency;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CurrencyTableModel extends AbstractTableModel {

    private List<Currency> currencyList;
    private List<String> columnsName = new ArrayList<>();

    public CurrencyTableModel() {
        columnsName.add("id");
        columnsName.add("Currency_name");
    }

    @Override
    public int getRowCount() {
        return currencyList.size();
    }

    @Override
    public int getColumnCount() {
        return columnsName.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Currency currency = currencyList.get(rowIndex);
        if (columnIndex == 0) {
            return currency.getId();
        }
        if(columnIndex == 1) {
            return currency.getCurrencyName();
        }
        throw new IllegalArgumentException("Wrong column index" + columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return columnsName.get(0);
        }
        if (column == 1) {
            return columnsName.get(1);
        }
        throw new IllegalArgumentException("Wrong column index" + column);
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }
}
