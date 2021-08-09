import interfaces.CartI;
import interfaces.PriceCalculatorI;
import model.Product;

import java.util.HashMap;
import java.util.Map;

public class BasicCalculator implements PriceCalculatorI {

    @Override
    public double getTotalPrice(CartI cartI) {
        double totalPrice = 0;
        HashMap<Product, Double> cartItems = cartI.getCart();

        for (Map.Entry<Product, Double> entry : cartItems.entrySet()) {
                totalPrice = totalPrice + entry.getKey().getPrice() * entry.getValue();
        }
        return totalPrice;
    }
}
