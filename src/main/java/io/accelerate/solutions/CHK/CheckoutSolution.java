package io.accelerate.solutions.CHK;

import io.accelerate.runner.SolutionNotImplementedException;
import java.util.HashMap;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        HashMap<String, Integer> basket = new HashMap<String, Integer>();
        String[] items = {"A", "B", "C", "D"};
        Integer[] prices = {50, 30, 20, 15};
        Integer total = 0;

        for (String sku : skus.split(",")) {
            if (!basket.containsKey(sku)) basket.put(sku, 0);
            else basket.put(sku, basket.get(sku) + 1);
        }

        for (int i = 0; i < items.length; i++){
            if (items[i].equals("A")) {
                total += 
            }
            else if (items[i].equals("B")) {

            }
            else {

            }
        }
        System.out.println(skus);


        return total;
    }
}



