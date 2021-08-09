import model.Cart;
import model.Product;
import org.junit.jupiter.api.*;

public class CartTest {

    Cart testCart;

    @BeforeEach
    void CreateCart() {
        testCart = new Cart();
    }

    @Test
    void testEmptyCartCreation() {
        Assertions.assertEquals(testCart.getTotalCount(), 0);
        Assertions.assertEquals(testCart.getUniqueCount(), 0);
    }
    @Test
    void testAddItemToCart(){
        Product productA = new Product('A', 50);
        testCart.addProduct(productA,1);
        Assertions.assertEquals(1, testCart.getTotalCount());
        Assertions.assertEquals(1, testCart.getUniqueCount());
    }

    @Test
    void testAddAndRemoveFromCart(){
        Product productA = new Product('A', 50);
        testCart.addProduct(productA,1);
        testCart.addProduct(productA,7);
        Assertions.assertEquals(testCart.getTotalCount(),8);

        testCart.removeProduct(productA,2);
        Assertions.assertEquals(testCart.getTotalCount(),6);
    }
}
