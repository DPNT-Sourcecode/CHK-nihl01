package io.accelerate.solutions.CHK;

import io.accelerate.runner.SolutionNotImplementedException;

import java.util.Arrays;
import java.util.HashMap;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        HashMap<Character, Integer> basket = new HashMap<Character, Integer>();
        Character[] items = {'A', 'B', 'C', 'D', 'E'};
        Integer[] prices = {50, 30, 20, 15, 40};
        Integer total = 0;

        for (Character item : items) {
            basket.put(item, 0);
        }

        for (int i = 0; i < skus.length(); i++) {
            if (!basket.containsKey(skus.charAt(i))) {
                return -1;
            }
            basket.put(skus.charAt(i), basket.get(skus.charAt(i)) + 1);
        }

        for (int i = 0; i < items.length; i++){
            Integer itemQuantity = basket.get(items[i]);
            switch (items[i]) {
                case 'A':
                    total += itemQuantity / 5 * 200;

                    total += ( ( itemQuantity % 5 ) / 3 ) * 130;

                    total += ( ( itemQuantity % 5 ) % 3 ) * prices[i];
                    break;
                case 'B':
                    total += ((itemQuantity/2)*45) + ((itemQuantity%2)*prices[i]);
                    break;
                case 'E':
                    basket.get('B') ;
                    total -= itemQuantity / 2 ;
                    total += prices[i] * itemQuantity;
                    break;
                default:
                    total += prices[i] * itemQuantity;
                    break;

            }
        }

        return total;
    }
}
