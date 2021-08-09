package interfaces;
import java.util.List;

public interface PromotionEngineCalculatorI {
    double getDiscountedPrice(CartI cartI, List<PromotionI> promotionIS);
}
