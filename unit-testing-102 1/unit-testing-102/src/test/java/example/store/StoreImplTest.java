package example.store;

import example.account.AccountManager;
import example.account.AccountManagerImpl;
import example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class StoreImplTest {

    @Test
    void givenProductWithValidQuantity_whenWithdrawingWithSuccess_thenReturnProductQuantityLessByOne() {
        //Given
        Product product = new Product();
        Customer customer = new Customer();
        product.setQuantity(7);
        product.setPrice(700);

        //When
        AccountManager accountManager = Mockito.mock(AccountManagerImpl.class);
        Mockito.when(accountManager.withdraw(customer, product.getPrice())).thenReturn("success");
        Store store = new StoreImpl(accountManager);
        store.buy(product,customer);

        //Then
        Assertions.assertEquals(6,product.getQuantity());
    }

    @Test
    void givenProductWithZeroQuantity_whenWithdrawing_thenThrowException() {
        //Given
        Product product = new Product();
        Customer customer = new Customer();
        product.setQuantity(0);
        product.setPrice(700);

        //When
        AccountManager accountManager = Mockito.mock(AccountManagerImpl.class);
        Mockito.when(accountManager.withdraw(customer, product.getPrice())).thenReturn("success");
        Store store = new StoreImpl(accountManager);

        //Then
        assertThrows(RuntimeException.class, () -> store.buy(product, customer));
    }

    @Test
    void givenProductWithValidQuantity_whenWithdrawDoesNotReturnSuccess_thenThrowException() {
        //Given
        Product product = new Product();
        Customer customer = new Customer();
        product.setQuantity(7);
        product.setPrice(700);

        //When
        AccountManager accountManager = Mockito.mock(AccountManagerImpl.class);
        Mockito.when(accountManager.withdraw(customer, product.getPrice())).thenReturn("insufficient account balance");
        Store store = new StoreImpl(accountManager);

        //Then
        assertThrows(RuntimeException.class, () -> store.buy(product, customer));
    }


}