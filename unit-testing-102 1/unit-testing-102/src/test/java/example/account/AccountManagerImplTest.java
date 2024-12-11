package example.account;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountManagerImplTest {

    @Test
    void deposit () {

    }

    @Test
    void withdraw_with_success_message_by_normal_customer() {
        AccountManager accountManager = new AccountManagerImpl();
        Customer customer = new Customer("ahsraf",2000,false,false);
        String resualt = accountManager.withdraw(customer,500);
        assertEquals("success", resualt);
    }

    @Test
    void withdraw_with_insufficient_balance_by_normal_customer() {
        AccountManager accountManager = new AccountManagerImpl();
        Customer customer = new Customer("ahsraf",1000,false,false);
        String resualt = accountManager.withdraw(customer,2000);
        assertEquals("insufficient account balance", resualt);
    }



}