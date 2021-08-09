package interfaces;
import java.util.List;

public interface IPromotionEngineCalculator {
    double getDiscountedPrice(ICart cartI, List<IPromotion> promotionIS);
}
