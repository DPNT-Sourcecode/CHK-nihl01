package io.accelerate.solutions.CHK;

import io.accelerate.runner.SolutionNotImplementedException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        if (skus == null) return -1;

        Map<Character, Integer> count = new HashMap<>();
        for (char c : skus.toCharArray()) {
            if (c < 'A' || c > 'Z') return -1;
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        // Unit prices
        Map<Character, Integer> price = Map.ofEntries(
                Map.entry('A', 50), Map.entry('B', 30), Map.entry('C', 20),
                Map.entry('D', 15), Map.entry('E', 40), Map.entry('F', 10),
                Map.entry('G', 20), Map.entry('H', 10), Map.entry('I', 35),
                Map.entry('J', 60), Map.entry('K', 70), Map.entry('L', 90),
                Map.entry('M', 15), Map.entry('N', 40), Map.entry('O', 10),
                Map.entry('P', 50), Map.entry('Q', 30), Map.entry('R', 50),
                Map.entry('S', 20), Map.entry('T', 20), Map.entry('U', 40),
                Map.entry('V', 50), Map.entry('W', 20), Map.entry('X', 17),
                Map.entry('Y', 20), Map.entry('Z', 21)
        );

        // ----- FREE ITEM OFFERS -----
        applyFree(count, 'E', 2, 'B');
        applyFree(count, 'N', 3, 'M');
        applyFree(count, 'R', 3, 'Q');
        applySelfFree(count, 'F', 2);
        applySelfFree(count, 'U', 3);

        int total = 0;

        // ----- MULTI-BUY OFFERS -----
        total += applyMulti(count, 'A', new int[][]{{5,200},{3,130}}, price);
        total += applyMulti(count, 'B', new int[][]{{2,45}}, price);
        total += applyMulti(count, 'H', new int[][]{{10,80},{5,45}}, price);
        total += applyMulti(count, 'K', new int[][]{{2,120}}, price);
        total += applyMulti(count, 'P', new int[][]{{5,200}}, price);
        total += applyMulti(count, 'Q', new int[][]{{3,80}}, price);
        total += applyMulti(count, 'V', new int[][]{{3,130},{2,90}}, price);

        // ----- REMAINING ITEMS -----
        for (var e : count.entrySet()) {
            total += e.getValue() * price.get(e.getKey());
        }

        return total;
    }

    private static void applyFree(Map<Character, Integer> count, char trigger, int qty, char free) {
        int freeItems = count.getOrDefault(trigger, 0) / qty;
        count.put(free, Math.max(0,
                count.getOrDefault(free, 0) - freeItems));
    }

    private static void applySelfFree(Map<Character, Integer> count, char sku, int qty) {
        int c = count.getOrDefault(sku, 0);
        count.put(sku, c - (c / (qty + 1)));
    }

    private static int applyMulti(Map<Character, Integer> count, char sku, int[][] offers, Map<Character, Integer> price) {
        int c = count.getOrDefault(sku, 0);
        int total = 0;

        for (int[] offer : offers) {
            int bundle = offer[0];
            int bundlePrice = offer[1];
            int times = c / bundle;
            total += times * bundlePrice;
            c %= bundle;
        }

        count.put(sku, c);
        return total;
    }

}





