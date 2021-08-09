import PromotionTypes.nItemsPromotion;
import model.Cart;
import model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class nItemsPromotionTest {
    private Product productA, productB;
    private Cart cart;
    @BeforeEach
    void initializeObjects(){
        productA = new Product('A', 50);
        cart = new Cart();
        productB = new Product('B', 30);
    }

    @Test
    void testActivePromotionA() {
        cart.addProduct(productA,5);
        nItemsPromotion nItemsPromotion = new nItemsPromotion(productA,3,130);
        Assertions.assertEquals(230,nItemsPromotion.calculateDiscountedPrice(cart));
    }

    @Test
    void testActivePromotionB(){
        cart.addProduct(productB,5);
        nItemsPromotion nItemsPromotion = new nItemsPromotion(productB, 2, 45);
        Assertions.assertEquals(120,nItemsPromotion.calculateDiscountedPrice(cart));
    }
}
