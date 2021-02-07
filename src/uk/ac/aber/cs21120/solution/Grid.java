package uk.ac.aber.cs21120.solution;

import uk.ac.aber.cs21120.interfaces.IGrid;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a 9x9 Sudoku grid
 * Contains methods for creating and validating a Sudoku grid
 * @author Ben Brackenbury
 * @version 1.0
 */
public class Grid implements IGrid {

    private int[][] grid;

    /**
     * Constructor for Grid class
     * Initialised the private grid instance variable with a 9x9 2d int array
     */
    public Grid() {
        this.grid = new int[9][9];
    }

    /**
     * Gets the value of a specific cell
     * @param x column number
     * @param y row number
     * @return integer value of cell
     * @throws BadCellException if coordinates are out of range
     */
    @Override
    public int get(int x, int y) throws BadCellException {
        if (x<0 || x>9 || y<0 || y>9) { throw new BadCellException(x, y); }
        return grid[x][y];
    }

    /**
     * Sets the value of a specific cell
     * @param x column number
     * @param y row number
     * @param val digit from 1-9, or 0 for an empty cell
     * @throws BadCellException if coordinates are out of range
     * @throws BadDigitException if value to set is out of range
     */
    @Override
    public void set(int x, int y, int val) throws BadCellException, BadDigitException {
        if (x<0 || x>8 || y<0 || y>8) { throw new BadCellException(x, y); }
        if (val<0 || val>9) { throw new BadDigitException(val); }
            grid[x][y] = val;
    }

    @Override
    public boolean isValid() {
        boolean result = true;
        for (int i = 0; i < grid.length; i++) {
            //check cols
            Set<Integer> rowSet = new HashSet<>();
            for (int j = 0; j < grid[i].length; j++) {
                int cell = grid[i][j];
                if (!rowSet.add(cell) && cell != 0) {
                    result = false;
                    break;
                }
            }
            if (!result) break;

            //check cols
            Set<Integer> colSet = new HashSet<>();
            for (int j = 0; j < grid[i].length; j++) {
                int cell = grid[j][i];
                if (!colSet.add(cell) && cell != 0) {
                    result = false;
                    break;
                }
            }
            if (!result) break;
        }

        //idk but it works
        if (result) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Set<Integer> subGridSet = new HashSet<>();
                    for (int x = 0; x < 3; x++) {
                        for (int y = 0; y < 3; y++) {
                            int cell = grid[i * 3 + x][j * 3 + y];
                            if (!subGridSet.add(cell) && cell != 0) {
                                result = false;
                                break;
                            }
                        }
                        if (!result) break;
                    }
                    if (!result) break;
                }
                if (!result) break;
            }
        }
        return result;
    }

    /**
     * Checks if the grid is valid Sudoku solution
     * Checks rows, columns, and 3x3 subgrids
     * @return boolean
     */
//    @Override
//    public boolean isValid() {
//        boolean result = true;
//        for (int i = 0; i < grid.length; i++) {
//            //check rows
//            ArrayList<Integer> rowArray = new ArrayList<>();
//            for (int j = 0; j < grid[i].length; j++) {
//                int cell = grid[i][j];
//                if (rowArray.contains(cell) && cell!=0) {
//                    result = false;
//                    break;
//                } else {
//                    rowArray.add(cell);
//                }
//            }
//            if (!result) break;
//
//            //check columns
//            ArrayList<Integer> colArray = new ArrayList<>();
//            for (int j = 0; j < grid[i].length; j++) {
//                int cell = grid[j][i];
//                if (colArray.contains(cell) && cell!=0) {
//                    result = false;
//                    break;
//                } else {
//                    colArray.add(cell);
//                }
//            }
//            if (!result) break;
//        }
//
//        //check 3x3 subgrids
//        if (result) {
//            for (int i = 0; i < 3; i++) {
//                for (int j = 0; j < 3; j++) {
//                    ArrayList<Integer> subGridArray = new ArrayList<>();
//                    for (int x = 0; x < 3; x++) {
//                        for (int y = 0; y < 3; y++) {
//                            int cell = grid[i * 3 + x][j * 3 + y];
//                            if (subGridArray.contains(cell) && cell!=0) {
//                                result = false;
//                                break;
//                            } else {
//                                subGridArray.add(cell);
//                            }
//                        }
//                        if (!result) break;
//                    }
//                    if (!result) break;
//                }
//                if (!result) break;
//            }
//        }
//        return result;
//    }

    /**
     * Overrides the default toString() method
     * Formats as 9x9 grid
     * @return formatted string of values representing a 9x9 Sudoku grid
     */
    @Override
    public String toString(){
        StringBuilder b = new StringBuilder();
        for (int y=0;y<9;y++){
            for (int x=0;x<9;x++){
                b.append(get(x,y));
            }
            b.append('\n');
        }
        return b.toString();
    }

}
