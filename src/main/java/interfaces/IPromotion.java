package interfaces;

public interface IPromotion {
    boolean isPromotionApplicable(ICart cartI);
    double calculateDiscountedPrice(ICart cartI);

}
