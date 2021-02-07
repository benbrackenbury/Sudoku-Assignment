package uk.ac.aber.cs21120.solution;

import uk.ac.aber.cs21120.interfaces.IGrid;
import uk.ac.aber.cs21120.interfaces.ISolver;

/**
 * Solves a given Sudoku grid
 * Contains method method for solving
 * @author Ben Brackenbury
 * @version 1.0
 */
public class Solver implements ISolver {

    private Grid grid;

    /**
     * Constructor for Solver class
     * Sets grid instance variable to Grid passed in to method call
     * @param grid grid to solve
     */
    public Solver(IGrid grid) {
        this.grid = (Grid) grid;
    }

    /**
     * Solves the given grid
     * @return true if the grid has been solved; false if it hasn't
     */
    @Override
    public boolean solve() {
        for (int j=0; j<9; j++) {
            for (int i=0; i<9; i++) {
                //for each cell
                if (grid.get(i, j) == 0) {
                    for (int x=1; x<=9; x++) {
                        grid.set(i, j, x);
                        if (grid.isValid()) {
                            boolean result = solve();
                            if (result) return true;
                        }
                    }
                    grid.set(i, j,0);
                    return false;
                }
            }
        }
        return true;
    }
}
