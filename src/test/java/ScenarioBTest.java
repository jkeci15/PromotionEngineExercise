import PromotionTypes.nItemsPromotion;
import PromotionTypes.togetherProductPromotion;
import interfaces.IPromotion;
import model.Cart;
import model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScenarioBTest {
    @Test
    void testScenarioB() {
        Product productA = new Product('A',50);
        Product productB = new Product('B',30);
        Product productC = new Product('C',20);
        Product productD = new Product('D', 15);

        Cart cart = new Cart();
        cart.addProduct(productA, 5);
        cart.addProduct(productB,5);
        cart.addProduct(productC,1);

        HashMap<Product,Double> discountedItems = new HashMap<>();
        discountedItems.put(productC,(double)1);
        discountedItems.put(productD,(double)1);
        List<IPromotion> promotions = new ArrayList<>();
        promotions.add(new nItemsPromotion(productA,3,130));
        promotions.add(new nItemsPromotion(productB,2,45));
        promotions.add(new togetherProductPromotion(discountedItems,30));
        PromotionCalculator calculator = new PromotionCalculator();
        Assertions.assertEquals(370, calculator.getDiscountedPrice(cart,promotions));
    }
}
