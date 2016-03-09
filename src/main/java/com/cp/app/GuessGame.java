package com.cp.app;

import com.cp.common.XConfig;

import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

/**
 * A Number-Guessing Game Application
 *
 * GuessGame is the main class that contains the entry point main() method
 *
 */
public class GuessGame {

    private int nUpperBound;

    private int nLowerBound;

    private int nUpper, nLower;

    private int nGuessNumber;

    /**
     * The main class to run the number guessing application application
     *
     * @param args
     */
    public static void main( String[] args ) {

        // Instantiate an instance of GuessGame
        try {
            GuessGame game = new GuessGame();
            game.newGame();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.exit(0);
    }

    public void newGame() {

        // Load parameters of upper bound and lower bound numbers
        nUpperBound = XConfig.getInstance().getUpperBound();
        nLowerBound = XConfig.getInstance().getLowerBound();

        if (nUpperBound <= nLowerBound) {
            System.out.println("The upper bound number " + nUpperBound + " is less than or equal to the lower bound number.");
            System.out.println("You must reconfigure the numbers.");
        }

        // Create an instance of scanner for input
        Scanner sc = new Scanner(System.in);

        // Loop to start the game
        while (true) {
            System.out.print("Are you ready for the game? ");

            // Convert the user's response to lower case to ignore the case
            String line = sc.nextLine().toLowerCase();
            if ("ready".equals(line.trim())) {
                start();
            }
            else if ("exit".equals(line.trim())) {
                break;
            }
            else {
                System.out.println("Please respond with \"ready\" to start the game or \"exit\" to quit the application.");
            }
        }
    }

    public void start() {

        // Get the initial upper and lower bound numbers
        nUpper = nUpperBound;
        nLower = nLowerBound;

        // Calculate the initial guess number
        nGuessNumber = getInitialNumber(nLowerBound, nUpperBound);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Computer: Is the number " + nGuessNumber);
            System.out.print("User: ");
            String input = sc.nextLine().trim().toLowerCase();

            if ("yes".equals(input) || "end".equals(input))  {
                return;
            }
            else if ("higher".equals(input)) {
                nLower = nGuessNumber;
                nGuessNumber = mean(nLower, nUpper, true);
            }
            else if ("lower".equals(input)) {
                nUpper = nGuessNumber;
                nGuessNumber = mean(nLower, nUpper, false);
            }
            else {
                System.out.println("Unrecognized response: " + input);
                System.out.println("Please respond with \"higher\", \"lower\", \"yes\", or \"end\"");
            }

            // Check if the user has responded a wrong answer in the current or previous round
            if (checkError(nLower, nUpper)) {
                System.out.println("You must have given at least one wrong answer. This round of game ends here.");
                break;
            }
        }
    }

    /**
     * Check if the user has responded a wrong answer in the current or previous round.
     *
     * @param lowVal
     * @param highVal
     * @return
     */
    public boolean checkError(int lowVal, int highVal) {

        if (lowVal==highVal)
            return true;

        return false;
    }

    /**
     * Get the mean value of two integer numbers.
     *
     * @param highVal
     * @param lowVal
     * @param higher: the direction of either higher or lower with true for higher and false for lower.
     * @return
     */
    public int mean(int lowVal, int highVal, boolean higher) {

        if (highVal==(lowVal + 1)) {
            return (higher ? highVal : lowVal);
        }

        return (highVal + lowVal) / 2;
    }

    /**
     * Generate and return an initial guess number.
     *
     * @param lowVal
     * @param highVal
     * @return
     */
    public int getInitialNumber(int lowVal, int highVal) {

        Random random = new Random();

        return lowVal + random.nextInt(highVal - lowVal + 1);
    }
}