package interfaces;

public interface PromotionI {
    boolean isPromotionApplicable(CartI cartI);
    double calculateDiscountedPrice(CartI cartI);

}
