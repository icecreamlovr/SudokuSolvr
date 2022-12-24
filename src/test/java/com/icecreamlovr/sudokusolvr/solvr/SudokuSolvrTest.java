package com.icecreamlovr.sudokusolvr.solvr;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SudokuSolvrTest {
  @Test
  public void testSudokuSolvrSuccess() {
    int[][] testInput = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}};
    int[][] output = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}};
    SudokuSolvr.solveSudoku(testInput);
    assertThat(testInput).isEqualTo(output);
  }

  @Test
  public void testSudokuSolvrValidations() {
    int[][] testInput1 = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}};
    IllegalArgumentException actualThrown1 = assertThrows(
            IllegalArgumentException.class, () -> SudokuSolvr.solveSudoku(testInput1));
    assertThat(actualThrown1.getMessage()).isEqualTo("Size should be 9 * 9.");

    int[][] testInput2 = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}};
    IllegalArgumentException actualThrown2 = assertThrows(
            IllegalArgumentException.class, () -> SudokuSolvr.solveSudoku(testInput2));
    assertThat(actualThrown2.getMessage()).isEqualTo("Size should be 9 * 9.");

    int[][] testInput3 = {
            {15, 13, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}};
    IllegalArgumentException actualThrown3 = assertThrows(
            IllegalArgumentException.class, () -> SudokuSolvr.solveSudoku(testInput3));
    assertThat(actualThrown3.getMessage()).isEqualTo("Input numbers should be between 0 and 9.");

    int[][] testInput4 = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, -7, -9}};
    IllegalArgumentException actualThrown4 = assertThrows(
            IllegalArgumentException.class, () -> SudokuSolvr.solveSudoku(testInput4));
    assertThat(actualThrown4.getMessage()).isEqualTo("Input numbers should be between 0 and 9.");
  }

  @Test
  public void testSudokuSolvrValidateRules() {
    int[][] testInput1 = {
            {5, 3, 5, 5, 7, 3, 2, 6, 1},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}};
    IllegalArgumentException actualThrown1 = assertThrows(
            IllegalArgumentException.class, () -> SudokuSolvr.solveSudoku(testInput1));
    assertThat(actualThrown1.getMessage()).isEqualTo("Input numbers didn't follow Sudoku rules.");

    int[][] testInput2 = {
            {5, 3, 0, 0, 7, 3, 2, 6, 1},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 6, 0, 8, 0, 3, 0, 0, 1},
            {7, 6, 0, 0, 2, 0, 0, 0, 0},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 6, 0, 4, 1, 9, 0, 0, 5},
            {0, 6, 0, 0, 8, 0, 0, 7, 9}};
    IllegalArgumentException actualThrown2 = assertThrows(
            IllegalArgumentException.class, () -> SudokuSolvr.solveSudoku(testInput2));
    assertThat(actualThrown2.getMessage()).isEqualTo("Input numbers didn't follow Sudoku rules.");

    int[][] testInput3 = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 6, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}};
    IllegalArgumentException actualThrown3 = assertThrows(
            IllegalArgumentException.class, () -> SudokuSolvr.solveSudoku(testInput3));
    assertThat(actualThrown3.getMessage()).isEqualTo("Input numbers didn't follow Sudoku rules.");
  }
}

