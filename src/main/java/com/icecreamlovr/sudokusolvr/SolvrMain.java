package com.icecreamlovr.sudokusolvr;

import com.icecreamlovr.sudokusolvr.solvr.SudokuSolvr;

import java.util.Arrays;

public class SolvrMain {
  public static void main(String[] args) {
    int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}};
    System.out.println("Input board:");
    prettyPrint(board);

    SudokuSolvr.solveSudoku(board);

    System.out.println("Solved:");
    prettyPrint(board);

  }

  private static void prettyPrint(int[][] board) {
    for (int[] line : board) {
      System.out.println(Arrays.toString(line));
    }
  }
}
