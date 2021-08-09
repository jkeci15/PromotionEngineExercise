package interfaces;
import model.Product;
import java.util.HashMap;

public interface CartI {
    void addProduct(Product product, double amount);
    void removeProduct(Product product, double amount);
    int getUniqueCount();
    double getTotalCount();
    HashMap<Product, Double> getCart();
    Double getProductAmount(Product product);
    boolean hasProduct(Product product);
    void updateProductAmount(Product product, double newAmount);
    CartI cloneCart();
}
