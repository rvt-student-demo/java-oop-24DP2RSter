package rvt;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    // Vienīgais atļautais instances mainīgais
    private Map<String, Item> items;

    public ShoppingCart() {
        this.items = new HashMap<>();
    }

    // 5. un 7. daļa: Pievieno vai palielina daudzumu
    public void add(String product, int price) {
        if (this.items.containsKey(product)) {
            // Ja produkts jau ir grozā, palielinām daudzumu (7. daļa)
            this.items.get(product).increaseQuantity();
        } else {
            // Ja jauns produkts, izveidojam jaunu Item ar daudzumu 1 (5. daļa)
            this.items.put(product, new Item(product, 1, price));
        }
    }

    // 5. daļa: Aprēķina kopējo groza cenu
    public int price() {
        int totalPrice = 0;
        for (Item item : this.items.values()) {
            totalPrice += item.price();
        }
        return totalPrice;
    }

    // 6. daļa: Izprintē visus groza elementus
    public void print() {
        for (Item item : this.items.values()) {
            System.out.println(item);
        }
    }
}