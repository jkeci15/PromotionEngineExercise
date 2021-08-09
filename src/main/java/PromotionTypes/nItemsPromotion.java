package PromotionTypes;

import interfaces.CartI;
import interfaces.PromotionI;
import model.Product;


public class nItemsPromotion implements PromotionI {
    protected Product product;
    protected double amount;
    protected double promotionPrice;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }


    public nItemsPromotion(Product product, double amount, double discountedPrice) {
        this.product = product;
        this.amount = amount;
        this.promotionPrice = discountedPrice;
    }

    @Override
    public boolean isPromotionApplicable(CartI cartI) {
        double cartAmount = 0;
        if (cartI.getCart().containsKey(this.product)){
            cartAmount = cartI.getCart().get(this.product);
            return cartAmount >=this.amount;
        }
        return false;
    }

    @Override
    public double calculateDiscountedPrice(CartI cartI) {
        double newPrice = 0;
        if (cartI.getCart().containsKey(this.product)){
            double cartAmount = cartI.getCart().get(this.product);
//            Finish calculating prices with the promotions
            if (cartAmount >= this.amount){
                double nrPromotionsApplied = Math.floor(cartAmount/this.amount);
                newPrice =
                        nrPromotionsApplied * this.promotionPrice +
                                this.product.getPrice() * (cartAmount % this.amount);
                cartI.updateProductAmount(this.product, cartAmount - nrPromotionsApplied * this.amount);
            }
//            Add full price to left out products
            else {
                newPrice = cartI.getCart().get(this.product) * this.product.getPrice();
            }
        }
        return newPrice;
    }
}
