package io.accelerate.solutions.CHK;

import io.accelerate.runner.SolutionNotImplementedException;
import java.util.HashMap;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        HashMap<String, Integer> basket = new HashMap<String, Integer>();
        for (String sku : skus.split(",")) {
            if (!basket.containsKey(sku)) basket.put(sku, 0);
            else basket.put(sku, basket.get(sku) + 1);
        }
        
        System.out.println(skus);


        return pr
    }
}

