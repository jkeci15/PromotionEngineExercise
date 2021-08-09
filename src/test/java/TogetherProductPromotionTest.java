import PromotionTypes.togetherProductPromotion;
import model.Cart;
import model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;


public class TogetherProductPromotionTest {
    @Test
    void testTogetherPromotion() {
        Product productC = new Product('C', 20);
        Product productD = new Product('D', 15);

        HashMap<Product,Double> discountedItems = new HashMap<>();
        discountedItems.put(productC,(double)1);
        discountedItems.put(productD,(double)1);

        togetherProductPromotion promotion = new togetherProductPromotion(discountedItems,30);
        Cart cart = new Cart();
        cart.addProduct(productC,1);
        cart.addProduct(productD,1);
        Assertions.assertEquals(30,promotion.calculateDiscountedPrice(cart));
    }
}
