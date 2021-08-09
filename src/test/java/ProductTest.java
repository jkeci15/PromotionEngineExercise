import model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {
    @Test
    void testProductCreation(){
        Product product = new Product('A', 50);
        Assertions.assertEquals('A',product.getSku());
        Assertions.assertEquals(50,product.getPrice());
    }
}
