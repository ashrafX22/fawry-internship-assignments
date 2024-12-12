package example.account;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountManagerImplTest {

    @Test
    void givenCustomerWithBalance_whenDepositing_thenBalanceIncreases() {
        // Given
        AccountManager accountManager = new AccountManagerImpl();
        Customer customer = new Customer("ashraf", 1000, false, false);

        // When
        accountManager.deposit(customer, 500);

        // Then
        assertEquals(1500, customer.getBalance());
    }

    @Test
    void givenNormalCustomerWithSufficientBalance_whenWithdrawing_thenSuccessMessageReturned() {
        // Given
        AccountManager accountManager = new AccountManagerImpl();
        Customer customer = new Customer("ashraf", 2000, false, false);

        // When
        String result = accountManager.withdraw(customer, 500);

        // Then
        assertEquals("success", result);
    }

    @Test
    void givenNormalCustomerWithInsufficientBalance_whenWithdrawing_thenInsufficientBalanceMessageReturned() {
        // Given
        AccountManager accountManager = new AccountManagerImpl();
        Customer customer = new Customer("ashraf", 1000, false, false);

        // When
        String result = accountManager.withdraw(customer, 2000);

        // Then
        assertEquals("insufficient account balance", result);
    }

    @Test
    void givenNormalCustomerWithCreditAllowed_whenWithdrawingWithinCreditLimit_thenSuccessMessageReturned() {
        // Given
        AccountManager accountManager = new AccountManagerImpl();
        Customer customer = new Customer("ashraf", 1000, true, false);

        // When
        String result = accountManager.withdraw(customer, 2000);

        // Then
        assertEquals("success", result);
    }

    @Test
    void givenNormalCustomerWithCreditAllowed_whenWithdrawingBeyondCreditLimit_thenMaximumCreditExceededMessageReturned() {
        // Given
        AccountManager accountManager = new AccountManagerImpl();
        Customer customer = new Customer("ashraf", 900, true, false);

        // When
        String result = accountManager.withdraw(customer, 2000);

        // Then
        assertEquals("maximum credit exceeded", result);
    }

    @Test
    void givenVipCustomerWithoutCreditAllowed_whenWithdrawingBeyondBalance_thenInsufficientBalanceMessageReturned() {
        // Given
        AccountManager accountManager = new AccountManagerImpl();
        Customer customer = new Customer("ashraf", 2000, false, true);

        // When
        String result = accountManager.withdraw(customer, 5000);

        // Then
        assertEquals("insufficient account balance", result);
    }

    @Test
    void givenVipCustomerWithCreditAllowed_whenWithdrawingLargeAmount_thenSuccessMessageReturned() {
        // Given
        AccountManager accountManager = new AccountManagerImpl();
        Customer customer = new Customer("ashraf", 2000, true, true);

        // When
        String result = accountManager.withdraw(customer, 50000);

        // Then
        assertEquals("success", result);
    }
}