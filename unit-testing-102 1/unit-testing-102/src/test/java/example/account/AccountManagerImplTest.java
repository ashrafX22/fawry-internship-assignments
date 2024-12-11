package example.account;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountManagerImplTest {

    @Test
    void deposit () {
        AccountManager accountManager = new AccountManagerImpl();
        Customer customer = new Customer("ahsraf",1000,false,false);
        accountManager.deposit(customer,500);
        assertEquals(1500, customer.getBalance());
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
        String result = accountManager.withdraw(customer,2000);
        assertEquals("insufficient account balance", result);
    }

    @Test
    void withdraw_with_maximum_credit_by_normal_customer() {
        AccountManager accountManager = new AccountManagerImpl();
        Customer customer = new Customer("ahsraf",1000,true,false);
        String result = accountManager.withdraw(customer,2000);
        assertEquals("success", result);
    }

    @Test
    void withdraw_with_maximum_credit_exceeded_by_normal_customer() {
        AccountManager accountManager = new AccountManagerImpl();
        Customer customer = new Customer("ahsraf",900,true,false);
        String result = accountManager.withdraw(customer,2000);
        assertEquals("maximum credit exceeded", result);
    }

    @Test
    void withdraw_with_credit_NOT_allowed_by_vip_customer() {
        AccountManager accountManager = new AccountManagerImpl();
        Customer customer = new Customer("ahsraf",2000,false,true);
        String result = accountManager.withdraw(customer,5000);
        assertEquals("insufficient account balance", result);
    }

    @Test
    void withdraw_with_credit_allowed_by_vip_customer(){
        AccountManager accountManager = new AccountManagerImpl();
        Customer customer = new Customer("ahsraf",2000,true,true);
        String result = accountManager.withdraw(customer,50000);
        assertEquals("success", result);
    }
}