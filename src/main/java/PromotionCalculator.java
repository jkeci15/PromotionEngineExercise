import PromotionTypes.nItemsPromotion;
import PromotionTypes.togetherProductPromotion;
import interfaces.ICart;
import interfaces.IPromotionEngineCalculator;
import interfaces.IPromotion;
import model.Product;
import java.util.List;

public class PromotionCalculator implements IPromotionEngineCalculator {
    @Override
    public double getDiscountedPrice(ICart cart, List<IPromotion> promotions) {
        double discountedPrice = 0;
        for (IPromotion promotion : promotions) {
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
