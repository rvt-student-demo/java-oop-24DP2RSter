package rvt;

import java.util.HashMap;

public class IOU {
    private HashMap<String, Double> debts;

    // Konstruktors
    public IOU() {
        this.debts = new HashMap<>();
    }

    // Saglabā parādu. Ja vārds jau eksistē, HashMap to automātiski pārrakstīs ar jauno summu
    public void setSum(String toWhom, double amount) {
        this.debts.put(toWhom, amount);
    }

    // Atgriež summu, cik esam parādā. 
    // Izmantojam getOrDefault, lai automātiski atgrieztu 0.0, ja cilvēks sarakstā netiek atrasts
    public double howMuchDoIOweTo(String toWhom) {
        return this.debts.getOrDefault(toWhom, 0.0);
    }
}