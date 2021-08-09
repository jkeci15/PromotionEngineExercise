import PromotionTypes.nItemsPromotion;
import PromotionTypes.togetherProductPromotion;
import interfaces.IPromotion;
import model.Cart;
import model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScenariosTest {

    private Product productA, productB, productC, productD;
    private Cart cart;
    private HashMap<Product,Double> discountedItems;
    private List<IPromotion> promotions;
    private PromotionCalculator calculator;
    @BeforeEach
    void initializeEnvironment(){
        productA = new Product('A',50);
        productB = new Product('B',30);
        productC = new Product('C',20);
        productD = new Product('D', 15);
        cart = new Cart();
        discountedItems = new HashMap<>();
        promotions = new ArrayList<>();
        calculator = new PromotionCalculator();
    }
    @Test
    void testScenarioA() {
        cart.addProduct(productA, 1);
        cart.addProduct(productB,1);
        cart.addProduct(productC,1);

        discountedItems.put(productC,(double)1);
        discountedItems.put(productD,(double)1);

        promotions.add(new nItemsPromotion(productA,3,130));
        promotions.add(new nItemsPromotion(productB,2,45));
        promotions.add(new togetherProductPromotion(discountedItems,30));

        Assertions.assertEquals(100, calculator.getDiscountedPrice(cart,promotions));
    }
    @Test
    void testScenarioB() {
        cart.addProduct(productA, 5);
        cart.addProduct(productB,5);
        cart.addProduct(productC,1);

        discountedItems.put(productC,(double)1);
        discountedItems.put(productD,(double)1);

        promotions.add(new nItemsPromotion(productA,3,130));
        promotions.add(new nItemsPromotion(productB,2,45));
        promotions.add(new togetherProductPromotion(discountedItems,30));

        Assertions.assertEquals(370, calculator.getDiscountedPrice(cart,promotions));
    }
    @Test
    void testScenarioC() {
        cart.addProduct(productA, 3);
        cart.addProduct(productB,5);
        cart.addProduct(productC,1);
        cart.addProduct(productD,1);

        discountedItems.put(productC,(double)1);
        discountedItems.put(productD,(double)1);

        promotions.add(new nItemsPromotion(productA,3,130));
        promotions.add(new nItemsPromotion(productB,2,45));
        promotions.add(new togetherProductPromotion(discountedItems,30));

        Assertions.assertEquals(280, calculator.getDiscountedPrice(cart,promotions));
    }
}
