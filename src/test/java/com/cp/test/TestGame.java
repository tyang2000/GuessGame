package com.cp.test;

import com.cp.app.GuessGame;
import com.cp.common.EConstants;
import com.cp.common.XConfig;
import org.junit.*;

/**
 * Unit test on functions or methods
 *
 * Note: AS the number of methods grows, will group testing by its class, or
 * create separate test class for each class.
 *
 */
public class TestGame {

    private static GuessGame game;
    private static int nUpperBound;
    private static int nLowerBound;

    @BeforeClass
    public static void start() throws Exception {

        game = new GuessGame();

        nUpperBound = XConfig.getInstance().getUpperBound();
        nLowerBound = XConfig.getInstance().getLowerBound();
    }

    @Test
    public void test_config() throws Exception {

        Assert.assertEquals(nUpperBound, EConstants.UPPER_BOUND.getValue());
        Assert.assertEquals(nLowerBound, EConstants.LOWER_BOUND.getValue());

        System.out.println("test_config passed");
    }

    @Test
    public void test_mean() throws Exception {

        int nLowVal = 40;
        int nHighVal = 50;

        int val = game.mean(nLowVal, nHighVal, true);
        Assert.assertTrue(nHighVal >= val && val >= nLowVal);

        val = game.mean(40, 41, true);
        Assert.assertEquals(val, 41);

        val = game.mean(40, 41, false);
        Assert.assertEquals(val, 40);

        System.out.println("test_mean passed");
    }

    @Test
    public void test_initialNumber() {

        int nLowVal = 1;
        int nHighVal = 100;

        int val = game.mean(nLowVal, nHighVal, true);
        Assert.assertTrue(nHighVal >= val && val >= nLowVal);

        System.out.println("test_initialNumber passed");
    }

    @Test
    public void test_checkError() {

        Assert.assertTrue(game.checkError(10, 10));

        System.out.println("test_checkError passed");
    }

    @AfterClass
    public static void stop() throws Exception {
    }
}
