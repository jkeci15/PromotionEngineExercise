import model.Cart;
import model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BasicCalculatorTest {
    BasicCalculator basicCalculator;
    Cart cart;
    Product productA;
    Product productB;
    Product productC;
    @BeforeEach
    void initializeScenario(){
        basicCalculator = new BasicCalculator();
        cart = new Cart();
        productA = new Product('A',50);
        productB = new Product('B',30);
        productC = new Product('C',20);
    }
    @Test
    void testCalculation() {
        cart.addProduct(productA,5);
        cart.addProduct(productB,5);
        cart.addProduct(productC,1);
        BasicCalculator basicCalculator = new BasicCalculator();
        Assertions.assertEquals(420 , basicCalculator.getTotalPrice(cart));
    }
}
