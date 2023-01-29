package com.icecreamlovr.sudokusolvr.cli;

import com.icecreamlovr.sudokusolvr.solvr.SudokuSolvr;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SudokuSolvrCli {
  public static void main(String[] args) throws ParseException {
    // Define one flag --input
    Options options = new Options();
    Option input = Option.builder()
            .longOpt("input")
            .desc("The input board. Use form [[1,2,3,..],[9,8,7,...],...]")
            .hasArg()
            .argName("BOARD")
            .build();
    options.addOption(input);

    // Parse the args with the flags defined above.
    CommandLineParser parser = new DefaultParser();
    CommandLine cmd = parser.parse(options, args);
    String inputBoard;
    if(cmd.hasOption("input")) {
      inputBoard = cmd.getOptionValue("input");
    } else {
      System.out.println("No input board supplied.");
      return;
    }

    // Parse the CLI input into the board we can work with.
    int[][] board = parseBoard(inputBoard);

    // Solve and print the result to STDOUT.
    System.out.println("Input board:");
    prettyPrint(board);
    SudokuSolvr.solveSudoku(board);
    System.out.println();
    System.out.println("Solved:");
    prettyPrint(board);
  }

  private static void prettyPrint(int[][] board) {
    for (int[] line : board) {
      System.out.println(Arrays.toString(line));
    }
  }

  // Parse square-brackets-enclosed, comma-separated sudoku board string
  // into 2 dimensional array.
  protected static int[][] parseBoard(String inputBoard) throws ParseException {
    // First remove whitespaces.
    String sanitized = inputBoard.replaceAll("\\s", "");

    // Parse the board into 9 rows for further processing.
    // The regex will match "[[row],[row],[row],[row],[row],[row],[row],[row],[row]]",
    // where row is /[0-9,]+/
    String singleRow = "\\[([0-9,]+)\\]";
    String singleRowWithComma = singleRow + ",";
    Pattern pattern =
            Pattern.compile(
                    "^\\["
                            + singleRowWithComma.repeat(8)
                            + singleRow
                            + "\\]$");

    Matcher matcher = pattern.matcher(sanitized);

    if (matcher.find() && matcher.groupCount() == 9) {
      // Parse each row.
      int resultBoard[][] = new int[9][9];

      for (int i = 0; i < 9; i++) {
        // matcher.group(i) is 1-indexed (group(0) is the matched string itself).
        int[] parsedRow = parseRow(matcher.group(i + 1));
        resultBoard[i] = parsedRow;
      }

      return resultBoard;
    } else {
      throw new ParseException("Not matched.");
    }
  }

  private static int[] parseRow(String oneRowInput) throws ParseException {
    Pattern pattern =
            Pattern.compile(
                    "([0-9]),([0-9]),([0-9]),([0-9]),([0-9]),([0-9]),([0-9]),([0-9]),([0-9])");
    Matcher matcher = pattern.matcher(oneRowInput);

    if (matcher.find() && matcher.groupCount() == 9) {
      int[] resultRow = new int[9];
      for (int i = 0; i < 9; i++) {
        // matcher.group(i) is 1-indexed (group(0) is the matched string itself).
        resultRow[i] = Integer.parseInt(matcher.group(i + 1));
      }
      return resultRow;
    } else {
      throw new ParseException("Not matched: " + oneRowInput);
    }
  }
}
