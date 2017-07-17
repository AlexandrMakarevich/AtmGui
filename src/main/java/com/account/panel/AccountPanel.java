package com.account.panel;

import com.account.AccountDao;
import com.account.listener.AddAccountButtonListener;
import com.account.listener.DeleteAccountButtonListener;
import com.account.table.AccountTable;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import javax.swing.*;
import java.awt.*;
import static com.account.listener.AddAccountButtonListener.ADD_ACCOUNT_BUTTON_LISTENER_BEAN_NAME;

public class AccountPanel extends JPanel {

    private FlowLayout accountLayout = new FlowLayout();
    private BorderLayout layout = new BorderLayout();
    private JLabel accountLabel = new JLabel("Enter account name");
    private JTextField accountInput = new JTextField("" , 5);
    private JButton addAccountButton = new JButton("Add account");
    private JButton deleteAccountButton = new JButton("Delete");
    private AddAccountButtonListener addAccountButtonListener;
    private AccountTable accountTable;

    public AccountPanel (ApplicationContext applicationContext) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
        setLayout(accountLayout);
        add(accountLabel);
        add(accountInput);
        add(addAccountButton);
        add(deleteAccountButton);
        addAccountButtonListener = (AddAccountButtonListener)
                applicationContext.getBean(ADD_ACCOUNT_BUTTON_LISTENER_BEAN_NAME);
        addAccountButtonListener.setInput(accountInput);
        accountTable = new AccountTable(namedParameterJdbcTemplate);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(accountTable);
        add(jScrollPane);
        addAccountButtonListener.setAccountTable(accountTable);
        addAccountButton.addActionListener(addAccountButtonListener);
        DeleteAccountButtonListener deleteAccountButtonListener =
                (DeleteAccountButtonListener) applicationContext.getBean("deleteAccountButtonListener");
        deleteAccountButtonListener.setAccountTable(accountTable);
        deleteAccountButton.addActionListener(deleteAccountButtonListener);
    }


}
