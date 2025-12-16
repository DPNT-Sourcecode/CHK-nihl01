package io.accelerate.solutions.CHK;

import io.accelerate.runner.SolutionNotImplementedException;

import java.util.Arrays;
import java.util.HashMap;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        HashMap<Character, Integer> basket = new HashMap<>();

        HashMap<Character, Integer> prices = new HashMap<>();

        Integer total = 0;

        char[] items = {
                'A','B','C','D','E','F','G','H','I','J',
                'K','L','M','N','O','P','Q','R','S','T',
                'U','V','W','X','Y','Z'
        };

        int[] values = {
                50,30,20,15,40,10,20,10,35,60,
                80,90,15,40,10,50,30,50,30,20,
                40,50,20,90,10,50
        };

        for (int i = 0; i < items.length; i++) {
            prices.put(items[i], values[i]);
            basket.put(items[i], 0);
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
                    if( itemQuantity / 2 > basket.get('B') ) {
                        basket.put('B', 0);
                    }
                    else{
                        basket.put('B', basket.get('B') - (itemQuantity / 2));
                    }
                    total += prices[i] * itemQuantity;
                    break;
                case 'F':
                    int freeF = itemQuantity / 3;
                    basket.put('F', itemQuantity - freeF);
                    total += prices[i] * basket.get('F');
                    break;
                default:
                    total += prices[i] * itemQuantity;
                    break;

            }
        }

        return total;
    }

    private static void applyFree(Map<Character, Integer> count,
                                  char trigger, int qty, char free) {
        int freeItems = count.getOrDefault(trigger, 0) / qty;
        count.put(free, Math.max(0,
                count.getOrDefault(free, 0) - freeItems));
    }

    private static void applySelfFree(Map<Character, Integer> count,
                                      char sku, int qty) {
        int c = count.getOrDefault(sku, 0);
        count.put(sku, c - (c / (qty + 1)));
    }

    

}


