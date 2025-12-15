package io.accelerate.solutions.CHK;

import io.accelerate.runner.SolutionNotImplementedException;
import java.util.HashMap;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        HashMap<String, Integer> basket = new HashMap<String, Integer>();
        String[] items = {"A", "B", "C", "D"};
        Integer[] prices = {50, 30, 20, 15};
        Integer total = 0;

        System.out.println(skus);

        for (String sku : skus.split("-")) {
            if (!basket.containsKey(sku)) {
                basket.put(sku, 1);
            }
            else basket.put(sku, basket.get(sku) + 1);
        }

        for (int i = 0; i < items.length; i++){
            if (items[i].equals("A")) {
                total += ((basket.get(items[i]) /3)*130) + ((basket.get(items[i])%3)*prices[i]);
            }
            else if (items[i].equals("B")) {
                total += ((basket.get(items[i])/2)*45) + ((basket.get(items[i])%2)*prices[i]);
            }
            else {
                total += prices[i]*basket.get(items[i]);
            }
        }



        return total;
    }
}

