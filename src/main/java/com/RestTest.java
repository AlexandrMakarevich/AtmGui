package com;

import com.account.Account;
import org.springframework.web.client.RestTemplate;

public class RestTest {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Account account = restTemplate.getForObject("http://localhost:8080/account1/Vova", Account.class);
        System.out.println(account);
    }
}
