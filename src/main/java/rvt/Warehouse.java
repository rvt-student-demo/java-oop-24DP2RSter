package rvt;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Warehouse {
    private Map<String, Integer> prices;
    private Map<String, Integer> stocks;

    public Warehouse() {
        this.prices = new HashMap<>();
        this.stocks = new HashMap<>();
    }

    // 1. daļa: Pievieno produktu
    public void addProduct(String product, int price, int stock) {
        this.prices.put(product, price);
        this.stocks.put(product, stock);
    }

    // 1. daļa: Atgriež cenu (vai -99, ja nav)
    public int price(String product) {
        if (!this.prices.containsKey(product)) {
            return -99;
        }
        return this.prices.get(product);
    }

    // 2. daļa: Atgriež atlikumu noliktavā
    public int stock(String product) {
        if (!this.stocks.containsKey(product)) {
            return 0;
        }
        return this.stocks.get(product);
    }

    // 2. daļa: Paņem produktu, samazinot atlikumu par 1
    public boolean take(String product) {
        if (this.stock(product) > 0) {
            int currentStock = this.stocks.get(product);
            this.stocks.put(product, currentStock - 1);
            return true;
        }
        return false;
    }

    // 3. daļa: Atgriež visu produktu nosaukumu sarakstu (Set)
    public Set<String> products() {
        return this.prices.keySet();
    }
}
