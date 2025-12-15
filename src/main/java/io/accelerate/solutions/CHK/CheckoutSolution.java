package io.accelerate.solutions.CHK;

import io.accelerate.runner.SolutionNotImplementedException;

import java.util.Arrays;
import java.util.HashMap;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        HashMap<Character, Integer> basket = new HashMap<Character, Integer>();
        Character[] items = {'A', 'B', 'C', 'D'};
        Integer[] prices = {50, 30, 20, 15};
        Integer total = 0;

        for (Character item : items) {
            basket.put(item, 0);
        }

        for (int i = 0; i < skus.length(); i++) {
            if (!basket.containsKey(skus.charAt(i))) {
                return -1;
            }
            else basket.put(skus.charAt(i), basket.get(skus.charAt(i)) + 1);
        }

        for (int i = 0; i < items.length; i++){
            if (items[i].equals('A')) {
                total += ((basket.get(items[i]) /3)*130) + ((basket.get(items[i])%3)*prices[i]);
            }
            else if (items[i].equals('B')) {
                total += ((basket.get(items[i])/2)*45) + ((basket.get(items[i])%2)*prices[i]);
            }
            else {
                total += prices[i]*basket.get(items[i]);
            }
        }

        return total;
    }
}
