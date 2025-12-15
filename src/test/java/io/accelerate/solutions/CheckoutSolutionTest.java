package io.accelerate.solutions;

import io.accelerate.solutions.CHK.CheckoutSolution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckoutSolutionTest {
    private CheckoutSolution checkout;

    @BeforeEach
    public void setUp() {
        checkout = new CheckoutSolution();
    }

    @Test
    public void compute_sum() {
        System.out.println(checkout.checkout("ABBEE"));
        assertThat(checkout.checkout("ABBEE").equals(160), equalTo(true));
    }
}

