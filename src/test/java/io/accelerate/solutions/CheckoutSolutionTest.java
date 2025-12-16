package io.accelerate.solutions;

import io.accelerate.solutions.CHK.CheckoutSolution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutSolutionTest {
    private CheckoutSolution checkout;

    @BeforeEach
    public void setUp() {
        checkout = new CheckoutSolution();
    }
// -------------------------
    // Illegal input tests
    // -------------------------

    @Test
    void illegalCharacterReturnsMinusOne() {
        assertEquals(-1, checkout.checkout("AxA"));
    }

    @Test
    void lowercaseInputReturnsMinusOne() {
        assertEquals(-1, checkout.checkout("abc"));
    }

    // -------------------------
    // Empty & single items
    // -------------------------


    @Test
    void singleItemPrices() {
        assertEquals(50, checkout.checkout("A"));
        assertEquals(30, checkout.checkout("B"));
        assertEquals(20, checkout.checkout("C"));
        assertEquals(15, checkout.checkout("D"));
        assertEquals(40, checkout.checkout("E"));
    }

    // -------------------------
    // A multi-buy offers
    // -------------------------

    @Test
    void threeAsFor130() {
        assertEquals(130, checkout.checkout("AAA"));
    }

    @Test
    void fiveAsFor200() {
        assertEquals(200, checkout.checkout("AAAAA"));
    }

    @Test
    void eightAsUsesBestCombination() {
        // 5A (200) + 3A (130)
        assertEquals(330, checkout.checkout("AAAAAAAA"));
    }

    @Test
    void fourAsUsesThreePlusOne() {
        // 3A (130) + 1A (50)
        assertEquals(180, checkout.checkout("AAAA"));
    }

    // -------------------------
    // B multi-buy offers
    // -------------------------

    @Test
    void twoBsFor45() {
        assertEquals(45, checkout.checkout("BB"));
    }

    @Test
    void threeBsBestPrice() {
        // 2B for 45 + 1B for 30
        assertEquals(75, checkout.checkout("BBB"));
    }

    // -------------------------
    // Cross-item offer: E -> B free
    // -------------------------

    @Test
    void twoEsGiveOneBFree() {
        assertEquals(80, checkout.checkout("EEB"));
    }

    @Test
    void freeBOnlyIfBExists() {
        assertEquals(80, checkout.checkout("EE"));
    }

    @Test
    void multipleFreeBsAppliedCorrectly() {
        // 4E gives 2 free Bs
        assertEquals(160, checkout.checkout("EEEEBB"));
    }

    @Test
    void freeBsDoNotGoNegative() {
        // 4E gives 2 free Bs but only 1 B exists
        assertEquals(160 + 0, checkout.checkout("EEEEB"));
    }

    // -------------------------
    // Combined offers
    // -------------------------

    @Test
    void bOfferAfterFreeBReduction() {
        // 2E -> 1 free B, remaining BB -> 45
        assertEquals(125, checkout.checkout("EEBBB"));
    }

    // -------------------------
    // Order independence
    // -------------------------

    @Test
    void orderDoesNotMatter() {
        assertEquals(
                checkout.checkout("ABCDEABCDE"),
                checkout.checkout("EDCBAEDCBA")
        );
    }
// -------------------------
    // F single & basic behavior
    // -------------------------

    @Test
    void singleFNoOffer() {
        assertEquals(10, checkout.checkout("F"));
    }

    @Test
    void twoFsNoFreeItem() {
        assertEquals(20, checkout.checkout("FF"));
    }

    @Test
    void threeFsOneFree() {
        // Pay for 2, get 1 free
        assertEquals(20, checkout.checkout("FFF"));
    }

    @Test
    void fourFsStillOnlyOneFree() {
        // 3 -> pay 2, plus 1 extra
        assertEquals(30, checkout.checkout("FFFF"));
    }

    @Test
    void sixFsTwoFree() {
        // 6 -> pay for 4
        assertEquals(40, checkout.checkout("FFFFFF"));
    }

    // -------------------------
    // F mixed with other items
    // -------------------------


    @Test
    void fAndAOffersTogether() {
        // 3F -> 20
        // 3A -> 130
        assertEquals(150, checkout.checkout("FFFAAA"));
    }

    // -------------------------
    // F + E interaction
    // -------------------------

    @Test
    void fAndEDoNotInteract() {
        // 3F -> 20
        // 2E -> 80
        assertEquals(100, checkout.checkout("FFFEE"));
    }

    // -------------------------
    // Combined stress cases
    // -------------------------

    @Test
    void complexBasketWithFAndB() {
        // FFF -> 20
        // BB -> 45
        // EE -> 80 (1 free B applied, but only 2 Bs)
        // Net B = 1 -> 30
        // Total = 20 + 80 + 30 = 130
        assertEquals(130, checkout.checkout("FFFBBEE"));
    }

    // -------------------------
    // Order independence
    // -------------------------

    @Test
    void orderIndependenceWithF() {
        assertEquals(
                checkout.checkout("FAFBFCFD"),
                checkout.checkout("DFCBAFFF")
        );
    }

    // ---------- BASIC FUNCTIONALITY ----------

    @Test
    void emptyBasketCostsZero() {
        assertEquals(0, checkout.checkout(""));
    }

    @Test
    void singleItems() {
        assertEquals(50, checkout.checkout("A"));
        assertEquals(30, checkout.checkout("B"));
        assertEquals(20, checkout.checkout("C"));
        assertEquals(15, checkout.checkout("D"));
        assertEquals(40, checkout.checkout("E"));
    }

    @Test
    void illegalInputReturnsMinusOne() {
        assertEquals(-1, checkout.checkout("a"));
        assertEquals(-1, checkout.checkout("ABCa"));
        assertEquals(-1, checkout.checkout("1"));
        assertEquals(-1, checkout.checkout(null));
    }

    // ---------- MULTI-BUY OFFERS ----------

    @Test
    void multiBuyA() {
        assertEquals(130, checkout.checkout("AAA"));
        assertEquals(200, checkout.checkout("AAAAA"));
        assertEquals(330, checkout.checkout("AAAAAAA")); // 5A + 2A
    }

    @Test
    void multiBuyB() {
        assertEquals(45, checkout.checkout("BB"));
        assertEquals(75, checkout.checkout("BBB"));
    }

    @Test
    void multiBuyH() {
        assertEquals(45, checkout.checkout("HHHHH"));
        assertEquals(80, checkout.checkout("HHHHHHHHHH"));
        assertEquals(90, checkout.checkout("HHHHHHHHHHH")); // 10 + 1
    }

    @Test
    void multiBuyK() {
        assertEquals(150, checkout.checkout("KK"));
        assertEquals(230, checkout.checkout("KKK"));
    }

    @Test
    void multiBuyP() {
        assertEquals(200, checkout.checkout("PPPPP"));
        assertEquals(250, checkout.checkout("PPPPPP"));
    }

    @Test
    void multiBuyQ() {
        assertEquals(80, checkout.checkout("QQQ"));
        assertEquals(110, checkout.checkout("QQQQ"));
    }

    @Test
    void multiBuyV() {
        assertEquals(90, checkout.checkout("VV"));
        assertEquals(130, checkout.checkout("VVV"));
        assertEquals(180, checkout.checkout("VVVV")); // 3 + 1
    }

    // ---------- FREE ITEM OFFERS ----------

    @Test
    void freeBWithTwoE() {
        assertEquals(80, checkout.checkout("EEB"));   // B free
        assertEquals(80, checkout.checkout("EEBB"));  // one B free, one paid
    }

    @Test
    void freeMWithThreeN() {
        assertEquals(120, checkout.checkout("NNNM")); // M free
        assertEquals(135, checkout.checkout("NNNMM"));
    }

    @Test
    void freeQWithThreeR() {
        assertEquals(150, checkout.checkout("RRRQ"));
        assertEquals(180, checkout.checkout("RRRQQ"));
    }

    @Test
    void selfFreeF() {
        assertEquals(20, checkout.checkout("FFF"));     // pay for 2
        assertEquals(30, checkout.checkout("FFFF"));    // pay for 3
        assertEquals(40, checkout.checkout("FFFFFF"));  // pay for 4
    }

    @Test
    void selfFreeU() {
        assertEquals(120, checkout.checkout("UUUU"));    // pay for 3
        assertEquals(160, checkout.checkout("UUUUU"));   // pay for 4
        assertEquals(200, checkout.checkout("UUUUUUU")); // pay for 5
    }

    // ---------- MIXED BASKETS ----------

    @Test
    void mixedOffersFavorCustomer() {
        assertEquals(260, checkout.checkout("AAAAAEEB")); // 5A + free B
    }

    @Test
    void mixedComplexBasket() {
        String basket = "ABCDABAA";
        // AAAAA = 200, BB = 45, C = 20, D = 15
        assertEquals(280, checkout.checkout(basket));
    }

    @Test
    void fullAlphabetOnce() {
        String basket = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        assertEquals(965, checkout.checkout(basket));
    }

    @Test
    void randomLargeBasket() {
        String basket = "AAABBBCCCCDDDEEEFFFHHHHHHVVVVVUUUU";
        int total = checkout.checkout(basket);
        assertTrue(total > 0);
        assertTrue(total < 2000);
    }

}


