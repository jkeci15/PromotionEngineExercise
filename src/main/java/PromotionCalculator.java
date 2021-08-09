import PromotionTypes.nItemsPromotion;
import PromotionTypes.togetherProductPromotion;
import interfaces.CartI;
import interfaces.PromotionEngineCalculatorI;
import interfaces.PromotionI;
import model.Product;
import java.util.List;

public class PromotionCalculator implements PromotionEngineCalculatorI {
    @Override
    public double getDiscountedPrice(CartI cart, List<PromotionI> promotions) {
        double discountedPrice = 0;
        for (PromotionI promotion : promotions) {
            if (promotion.isPromotionApplicable(cart)) {
                // Apply promotion
                discountedPrice += promotion.calculateDiscountedPrice(cart);
                System.out.println(discountedPrice);
                if (promotion instanceof togetherProductPromotion){
                    // remove products of the same promotion from cart
                    // since they are already accounted for
                    for (Product product : ((togetherProductPromotion) promotion).getPromotionItems().keySet()){
                        cart.updateProductAmount(product, 0);
                    }
                } else {
                    //in case of nItemsPromotion
                    cart.updateProductAmount(((nItemsPromotion)promotion).getProduct(), 0);
                }
            }
        }

        for(Product product : cart.getCart().keySet()) {
            discountedPrice += product.getPrice() * cart.getCart().get(product);
        }
        return discountedPrice;
    }
}
