package org.example;

import org.example.model.challange.Account;

public class Application {
    public static void main(String[] args) {
        //    Iterable var
        Account kontoAdama = new Account();
        kontoAdama.depositBalance(1000);
        kontoAdama.withdrawFounds(200);
        kontoAdama.depositBalance(2000);
        kontoAdama.withdrawFounds(500);


    }
}