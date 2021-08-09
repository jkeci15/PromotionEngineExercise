package PromotionTypes;

import interfaces.CartI;
import interfaces.PromotionI;
import model.Product;
import java.util.HashMap;
import java.util.Map;

public class togetherProductPromotion implements PromotionI {
    protected HashMap<Product, Double> promotionItems;
    protected double promotionPrice;

    public HashMap<Product, Double> getPromotionItems() {
        return promotionItems;
    }

    public double getPromotionPrice() {
        return promotionPrice;
    }

    public togetherProductPromotion(HashMap<Product, Double> promotionItems, double promotionPrice) {
        this.promotionItems = promotionItems;
        this.promotionPrice = promotionPrice;
    }

    public double getPromotionProductAmount(Product product){
        if (this.promotionItems.containsKey(product)){
            return this.promotionItems.get(product);
        }
        else{
           return 0;
        }
    }

    public boolean doesCartHaveAllProducts(CartI cartI){
        for (Map.Entry<Product, Double> entry : this.promotionItems.entrySet()) {
            Product key = entry.getKey();
            double reqAmount = this.getPromotionProductAmount(key);
            if (!cartI.hasProduct(key) || reqAmount > cartI.getProductAmount(key)) {
                return false;
            }
        }
        return true;
    }
//    To get the applicable number of promotions for the bundle promotion
    public int nrOfPromotionsApplicable(CartI cartI){
        int minPromotions = Integer.MAX_VALUE;
        for (Map.Entry<Product, Double> entry : this.promotionItems.entrySet()) {
            Product key = entry.getKey();
            double reqAmount = this.getPromotionProductAmount(key);
            if (!cartI.hasProduct(key) || reqAmount > cartI.getProductAmount(key)) {
                return 0;
            } else {
                minPromotions = Math.min(minPromotions, (int)Math.floor(cartI.getProductAmount(key)/reqAmount));
            }
        }
        return minPromotions;
    }

    @Override
    public boolean isPromotionApplicable(CartI cartI) {
        return this.doesCartHaveAllProducts(cartI);
    }

    @Override
    public double calculateDiscountedPrice(CartI cart) {
        double totalPrice = 0;

        int minPromotionsApplicable = nrOfPromotionsApplicable(cart);
        // Add discounted promotion prices to total first.
        totalPrice += this.promotionPrice * minPromotionsApplicable;

        for (Map.Entry<Product,Double> entry: this.promotionItems.entrySet()){
            Product product = entry.getKey();
            double amount = entry.getValue();
            double amountForPromotion = this.promotionItems.get(product);

            //Add remaining products at full price
            totalPrice += product.getPrice() * (amount - (amountForPromotion * minPromotionsApplicable));
        }

        return totalPrice;
    }
}
