package com.icecreamlovr.sudokusolvr.cli;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Test;

class SudokuSolvrCliTest {
  @Test
  public void testParseBoardSuccess() throws ParseException {
    String board = "[[1,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]]";
    int[][] expected = new int[9][9];
    expected[0][0] = 1;
    assertThat(SudokuSolvrCli.parseBoard(board))
          .isEqualTo(expected);
  }

  @Test
  public void testParseBoardRemovesWhitespace() throws ParseException {
    String board = "[[2,0 ,0,0,0,0,0,0,0],\n" +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]]";
    int[][] expected = new int[9][9];
    expected[0][0] = 2;
    assertThat(SudokuSolvrCli.parseBoard(board))
            .isEqualTo(expected);
  }

  @Test
  public void testParseBoardFailure() throws ParseException {
    // incorrect number of lines
    String board1 = "[[1,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]]";
    ParseException actualException1 = assertThrows(
            ParseException.class, () -> SudokuSolvrCli.parseBoard(board1));
    assertThat(actualException1.getMessage()).isEqualTo("Not matched.");

    // missing []
    String board2 = "[[1,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]]";
    ParseException actualException2 = assertThrows(
            ParseException.class, () -> SudokuSolvrCli.parseBoard(board2));
    assertThat(actualException2.getMessage()).isEqualTo("Not matched.");

    // Numbers are wrong
    String board3 = "[[1,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,30,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]," +
            "[0,0,0,0,0,0,0,0,0]]";
    ParseException actualException3 = assertThrows(
            ParseException.class, () -> SudokuSolvrCli.parseBoard(board3));
    assertThat(actualException3.getMessage()).isEqualTo("Not matched: 0,0,0,0,30,0,0,0");
  }
}
