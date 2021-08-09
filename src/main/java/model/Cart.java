package model;

import interfaces.CartI;
import java.util.HashMap;
import java.util.Map;

public class Cart implements CartI {
    protected HashMap<Product, Double> cart;

    public Cart() {
        this.cart = new HashMap<>();
    }

    @Override
    public void addProduct(Product product, double amount) {
        double newAmount = amount;
        if(this.cart.containsKey(product)){
            double oldAmount = this.cart.get(product);
            newAmount = oldAmount + newAmount;
        }
        this.cart.put(product,newAmount);
    }

    @Override
    public void removeProduct(Product product, double amount) {
        double oldAmount;
        double newAmount = 0;
        if (this.cart.containsKey(product)){
            oldAmount = this.cart.get(product);
            newAmount = oldAmount - amount >= 0 ? oldAmount - amount: 0;
        }
        this.updateProductAmount(product,newAmount);
    }

    @Override
    public int getUniqueCount() {
        return this.cart.size();
    }

    @Override
    public double getTotalCount() {
        double totalCount = 0;
        for (Map.Entry<Product, Double> entry : this.cart.entrySet()) {
            Double integer = entry.getValue();
            totalCount = totalCount + integer;
        }
        return totalCount;
    }

    @Override
    public HashMap<Product, Double> getCart() {
        return this.cart;
    }

    @Override
    public Double getProductAmount(Product product) {
        return this.cart.getOrDefault(product, (double) 0);

    }

    @Override
    public boolean hasProduct(Product product) {
        return this.getCart().containsKey(product);
    }

    @Override
    public void updateProductAmount(Product product, double newAmount) {
        if (newAmount>0){
            this.cart.put(product,newAmount);
        }
        else {
            this.cart.remove(product);
        }
    }

    @Override
    public CartI cloneCart() {
        Cart newCart = new Cart();
        for (Map.Entry<Product, Double> entry : this.cart.entrySet()) {
            Product key = entry.getKey();
            Double value = entry.getValue();
            newCart.addProduct(key, value);
        }
        return newCart;
    }
}
