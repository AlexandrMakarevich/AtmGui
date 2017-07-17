package com.account.panel;

import com.account.listener.AddAccountButtonListener;
import com.account.listener.DeleteAccountButtonListener;
import com.account.table.AccountTable;
import com.account.table.AccountTableModel;
import org.springframework.context.ApplicationContext;
import javax.swing.*;
import java.awt.*;
import static com.account.listener.AddAccountButtonListener.ADD_ACCOUNT_BUTTON_LISTENER_BEAN_NAME;
import static com.account.listener.DeleteAccountButtonListener.DELETE_ACCOUNT_BUTTON_LISTENER_BEAN;
import static com.account.table.AccountTable.ACCOUNT_TABLE_BEAN_NAME;
import static com.account.table.AccountTableModel.ACCOUNT_TABLE_MODEL_BEAN_NAME;

public class AccountPanel extends JPanel {

    private FlowLayout accountLayout = new FlowLayout();
    private JLabel accountLabel = new JLabel("Enter account name");
    private JTextField accountInput = new JTextField("", 5);
    private JButton addAccountButton = new JButton("Add account");
    private JButton deleteAccountButton = new JButton("Delete");
    private AddAccountButtonListener addAccountButtonListener;
    private DeleteAccountButtonListener deleteAccountButtonListener;
    private AccountTable accountTable;
    private AccountTableModel accountTableModel;

    public AccountPanel(ApplicationContext applicationContext) {
        addComponentsOnPanel(applicationContext);
        addAccountButtonListener = (AddAccountButtonListener)
                applicationContext.getBean(ADD_ACCOUNT_BUTTON_LISTENER_BEAN_NAME);
        deleteAccountButtonListener =
                (DeleteAccountButtonListener) applicationContext.getBean(DELETE_ACCOUNT_BUTTON_LISTENER_BEAN);
        initAndAddListener();
    }

    public void addComponentsOnPanel(ApplicationContext applicationContext) {
        accountTable = (AccountTable) applicationContext.getBean(ACCOUNT_TABLE_BEAN_NAME);
        accountTableModel = (AccountTableModel) applicationContext.getBean(ACCOUNT_TABLE_MODEL_BEAN_NAME);
        accountTable.setModel(accountTableModel);
        accountTable.refreshModel();
        setLayout(accountLayout);
        add(accountLabel);
        add(accountInput);
        add(addAccountButton);
        add(deleteAccountButton);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(accountTable);
        add(jScrollPane);
    }

    public void initAndAddListener() {
        addAccountButtonListener.setInput(accountInput);
        addAccountButtonListener.setAccountTable(accountTable);
        addAccountButton.addActionListener(addAccountButtonListener);
        deleteAccountButtonListener.setAccountTable(accountTable);
        deleteAccountButton.addActionListener(deleteAccountButtonListener);
    }
}
