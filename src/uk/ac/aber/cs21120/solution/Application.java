package uk.ac.aber.cs21120.solution;

import uk.ac.aber.cs21120.solution.menu.Menu;
import uk.ac.aber.cs21120.tests.Examples;

/**
 * Main Application class
 * Displays the main menu and runs all of the program's functionality
 * @author Ben Brackenbury
 * @version 1.0
 */
public class Application {
    private boolean loopMainMenu = true;

    /**
     * Gets a specified number of puzzles from the examples
     * Creates a solver and solves the puzzles, timing how long it takes to complete
     * @param numPuzzles the number of puzzles to solve
     */
    private void solveWithTimer(int numPuzzles) {
        long start = System.currentTimeMillis();
        long timeTaken;
        for (int i = 0; i < numPuzzles; i++) {
            long currentPuzzleStart = System.nanoTime();
            Solver solver = new Solver(Examples.getExample(i));
            solver.solve();
            timeTaken = System.nanoTime() - currentPuzzleStart;
            System.out.println(Examples.getGapCount(i) + ", " + timeTaken/1e6);
        }
        timeTaken = System.currentTimeMillis() - start;
        System.out.println();
        System.out.println("=================================");
        System.out.println("Total Time: " + timeTaken + "ms (" + timeTaken/1000 + "s)");
    }

    /**
     * Displays the application's main menu until the user quits
     */
    private void displayMainMenu() {

        Menu mainMenu = new Menu("Choose an action", new String[]{
                "Solve 400 puzzles with timer",
                "Solve 50 puzzles with timer",
                "Quit",
        });
        switch (mainMenu.run()) {
            case 1 -> solveWithTimer(400);
            case 2 -> solveWithTimer(50);
            case 3 -> loopMainMenu = false;
            default -> System.err.println("Try again");
        }
        System.out.println();
    }

    public void run() {
//        while (loopMainMenu) {
//            displayMainMenu();
//        }
//        this.quit();
        solveWithTimer(400);
    }

//    private void quit() {
//        System.out.println("Goodbye");
//        System.exit(0);
//    }

    /**
     * Main function called when application is run
     * Creates new instance of Application and calls the displayMainMenu() method
     * @param args optional program arguments
     */
    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }
}