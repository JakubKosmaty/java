import java.util.HashMap;
import java.util.Map;

public class Shop implements ShopInterface {
    private final Map<String, Integer> productsMap = new HashMap<>();

    private boolean isProductsAvailable(String product, int quantity) {
        return (productsMap.get(product) != null && productsMap.get(product) >= quantity);
    }

    private void addOrTakeOffProducts(String product, int quantity) {
        productsMap.replace(product, productsMap.get(product) + quantity);
    }

    private void addProductsToShop(String product, int quantity) {
        if (productsMap.get(product) == null) {
            productsMap.put(product, quantity);
        } else {
            addOrTakeOffProducts(product, quantity);
        }
    }

    @Override
    public synchronized boolean purchase(String productName, int quantity, Customer customerType) {
        if (isProductsAvailable(productName, quantity)) {
            addOrTakeOffProducts(productName, -quantity);
            return true;
        }

        if (customerType == Customer.IMPATIENT) {
            return false;
        }

        while (!isProductsAvailable(productName, quantity)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        addOrTakeOffProducts(productName, -quantity);
        return true;
    }

    @Override
    public synchronized void deliveryOfGoods(String productName, int quantity) {
        addProductsToShop(productName, quantity);
        notifyAll();
    }

    @Override
    public synchronized Map<String, Integer> stockStatus() {
        return productsMap;
    }
}
