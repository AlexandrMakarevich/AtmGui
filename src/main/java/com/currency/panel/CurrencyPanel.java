package com.currency.panel;

import com.currency.listener.AddCurrencyButtonListener;
import com.currency.listener.DeleteCurrencyButtonListener;
import com.currency.table.CurrencyTable;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import javax.swing.*;
import java.awt.*;

public class CurrencyPanel extends JPanel {

    private JButton currencyButton = new JButton("Add currency");
    private JButton deleteButton = new JButton("Delete");
    private JLabel currencyLabel = new JLabel("Enter currency name");
    private FlowLayout currencyLayout = new FlowLayout();
    private JTextField input = new JTextField("", 5);
    private CurrencyTable table;

    public CurrencyPanel(ApplicationContext applicationContext) {
        setLayout(currencyLayout);
        add(currencyLabel);
        add(input);
        add(currencyButton);
        add(deleteButton);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                (NamedParameterJdbcTemplate) applicationContext.getBean("namedParameterJdbcTemplate");
        table = new CurrencyTable(namedParameterJdbcTemplate);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(table);
        add(jScrollPane);
        AddCurrencyButtonListener addCurrencyButtonListener =
                (AddCurrencyButtonListener) applicationContext.getBean("addCurrencyButtonListener");
        addCurrencyButtonListener.setInput(input);
        addCurrencyButtonListener.setCurrencyTable(table);
        currencyButton.addActionListener(addCurrencyButtonListener);
        DeleteCurrencyButtonListener deleteCurrencyButtonListener =
                (DeleteCurrencyButtonListener) applicationContext.getBean("deleteCurrencyButtonListener");
        deleteCurrencyButtonListener.setCurrencyTable(table);
        deleteButton.addActionListener(deleteCurrencyButtonListener);
    }
}
