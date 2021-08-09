import interfaces.ICart;
import interfaces.IPriceCalculator;
import model.Product;

import java.util.HashMap;
import java.util.Map;

public class BasicCalculator implements IPriceCalculator {

    @Override
    public double getTotalPrice(ICart cartI) {
        double totalPrice = 0;
        HashMap<Product, Double> cartItems = cartI.getCart();

        for (Map.Entry<Product, Double> entry : cartItems.entrySet()) {
                totalPrice = totalPrice + entry.getKey().getPrice() * entry.getValue();
        }
        return totalPrice;
    }
}
