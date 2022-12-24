package com.icecreamlovr.sudokusolvr.solvr;

import java.util.ArrayList;
import java.util.List;

public class SudokuSolvr {

  private final static int LENGTH = 9;

  private static class Coords {
    private int row;
    private int col;

    public Coords(int row, int col) {
      this.row = row;
      this.col = col;
    }

    @Override
    public String toString() {
      return (row + "," + col);
    }
  }


  /**
   * Given a sudoku input, attempt to solve it using DFS + backtracking.
   * If successful, return the solved board.
   */
  public static void solveSudoku(int[][] board) {
    checkBoardSize(board);
    checkNumberRange(board);
    checkSudokuRules(board);

    List<Coords> solvingOrder = getSolvingOrder(board);
    solveRecur(board, solvingOrder, 0);
  }



  // Verifies that the board size is 9x9.
  private static void checkBoardSize(int[][] board) {
    if (board.length != 9) {
      throw new IllegalArgumentException("Size should be 9 * 9.");
    }
    for (int i = 0; i < 9; i++) {
      if (board[i].length != 9) {
        throw new IllegalArgumentException("Size should be 9 * 9.");
      }
    }
  }

  // Verifies that the numbers in the input are between 0-9 (0 meaning empty).
  private static void checkNumberRange(int[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (board[i][j] > 9 || board[i][j] < 0) {
          throw new IllegalArgumentException("Input numbers should be between 0 and 9.");
        }
      }
    }
  }

  // Verifies that the input board follows Sudoku rules (row/col/3x3).
  private static void checkSudokuRules(int[][] board) {
    // checks rows
    for (int i = 0; i < board.length; i++) {
      boolean[] hasNumber = new boolean[board.length];
      for (int j = 0; j < board.length; j++) {
        int boardNumber = board[i][j];
        if (boardNumber != 0) {
          if (hasNumber[boardNumber - 1]) {
            throw new IllegalArgumentException("Input numbers didn't follow Sudoku rules.");
          }
          hasNumber[boardNumber - 1] = true;
        }
      }
    }
    // checks columns
    for (int i = 0; i < board.length; i++) {
      boolean[] hasNumber = new boolean[board.length];
      for (int j = 0; j < board.length; j++) {
        int boardNumber = board[j][i];
        if (boardNumber != 0) {
          if (hasNumber[boardNumber - 1]) {
            throw new IllegalArgumentException("Input numbers didn't follow Sudoku rules.");
          }
          hasNumber[boardNumber - 1] = true;
        }
      }
    }
    // check 3x3 boxes
    for (int k = 0; k < 3; k++) {
      for (int l = 0; l < 3; l++) {
        boolean[] hasNumber = new boolean[board.length];
        for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
            int boardNumber = board[i + 3 * k][j + 3 * l];
            if (boardNumber != 0) {
              if (hasNumber[boardNumber - 1]) {
                throw new IllegalArgumentException("Input numbers didn't follow Sudoku rules.");
              }
              hasNumber[boardNumber - 1] = true;
            }
          }
        }
      }
    }
  }

  // Get an ordered list of coordinates that need to be solved.
  // The algorithm will follow this order to solve the sudoku.
  private static List<Coords> getSolvingOrder(int[][] board) {
    List<Coords> order = new ArrayList<>();
    for (int i = 0; i < LENGTH; i++) {
      for (int j = 0; j < LENGTH; j++) {
        if (board[i][j] == 0) {
          order.add(new Coords(i, j));
        }
      }
    }
    return order;
  }

  // Recursively solves the sudoku using DFS + backtracking.
  // The algorithm will follow a specific order to determine what slot to solve next.
  // The recursive call returns false if it cannot move forward.
  private static boolean solveRecur(int[][] board, List<Coords> solvingOrder, int orderIndex) {
    if (orderIndex == solvingOrder.size()) {
      // Base case: no more slot to solve.
      return true;
    }

    Coords boardSlot = solvingOrder.get(orderIndex);
    List<Integer> guesses = getGuesses(boardSlot, board);
    if (guesses.isEmpty()) {
      // Base case: impossible to solve further.
      return false;
    }

    for (int guess : guesses) {
      // Make a guess, then call solve recursively.
      board[boardSlot.row][boardSlot.col] = guess;
      if (!solveRecur(board, solvingOrder, orderIndex + 1)) {
        // If the child solve call returns false, revert the guess.
        board[boardSlot.row][boardSlot.col] = 0;
      } else {
        return true;
      }
    }
    return false;
  }

  // For a given unsolved slot, gets the list of potential numbers it can be.
  // This is done by going through the row, the column and the 3x3 square this slot is in.
  private static List<Integer> getGuesses(Coords boardSlot, int[][] board) {
    // Create a boolean array for masking.
    boolean[] existingNumbers = new boolean[9];
    int row = boardSlot.row;
    int col = boardSlot.col;

    // Go through the rows and columns.
    for (int i = 0; i < LENGTH; i++) {
      int currentRow = board[row][i];
      if (currentRow != 0) {
        existingNumbers[currentRow - 1] = true;
      }
      int currentCol = board[i][col];
      if (currentCol != 0) {
        existingNumbers[currentCol - 1] = true;
      }
    }

    // Go through the 3x3 square.
    for (int i = row / 3 * 3; i <= row / 3 * 3 + 2; i++) {
      for (int j = col / 3 * 3; j <= col / 3 * 3 + 2; j++) {
        int current = board[i][j];
        if (current != 0) {
          existingNumbers[current - 1] = true;
        }
      }
    }

    // Transform the boolean mask array to list of numbers.
    List<Integer> possibleGuesses = new ArrayList<>();
    for (int i = 0; i < existingNumbers.length; i++) {
      if (!existingNumbers[i]) {
        possibleGuesses.add(i + 1);
      }
    }
    return possibleGuesses;
  }
}
