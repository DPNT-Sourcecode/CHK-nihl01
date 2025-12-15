package io.accelerate.solutions;

import io.accelerate.solutions.CHK.CheckoutSolution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void emptyBasketCostsZero() {
        assertEquals(0, checkout.checkout(""));
    }

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
    void combinedAandEOffers() {
        // 3A = 130, 2E = 80, B free
        assertEquals(210, checkout.checkout("AAEEEB"));
    }

    @Test
    void bOfferAfterFreeBReduction() {
        // 2E -> 1 free B, remaining BB -> 45
        assertEquals(125, checkout.checkout("EEBBB"));
    }

    @Test
    void complexMixedBasket() {
        // AAAAA = 200
        // BBB = 45 + 30 = 75
        // EE = 80 (1 free B already applied)
        // C = 20
        // D = 15
        assertEquals(390, checkout.checkout("AAAAABBBEECD"));
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



}
