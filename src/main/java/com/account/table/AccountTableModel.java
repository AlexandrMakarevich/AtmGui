package com.account.table;

import com.account.Account;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class AccountTableModel extends AbstractTableModel {

    private List<String> columnName = new ArrayList<>();
    private List<Account> accountList;

    public AccountTableModel() {
        columnName.add("id");
        columnName.add("Account name");
    }

    @Override
    public int getRowCount() {
        return accountList.size();
    }

    @Override
    public int getColumnCount() {
        return columnName.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Account account = accountList.get(rowIndex);
        if (columnIndex == 0) {
           return account.getId();
        }
        if (columnIndex == 1) {
           return account.getAccountName();
        }
        throw new IllegalArgumentException("Wrong column index" + columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0){
            return columnName.get(0);
        }
        if (column == 1) {
            return  columnName.get(1);
        }
        throw new IllegalArgumentException("Wrong column index" + column);
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
