package com;

import com.account.listener.AddAccountButtonListener;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:atmGui.xml")
public class Test {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Resource
    private AddAccountButtonListener addAccountButtonListener;

    @org.junit.Test
    public void test() {
//        addAccountButtonListener.addAccount(namedParameterJdbcTemplate, "Petya");
    }
}
