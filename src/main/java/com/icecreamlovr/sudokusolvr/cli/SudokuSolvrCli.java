package com.icecreamlovr.sudokusolvr.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class SudokuSolvrCli {
  public static void main(String[] args) throws ParseException {
    System.out.println(">>>Hello world CLI!");

    Options options = new Options();
    options.addOption("x", false, "random flag");

    CommandLineParser parser = new DefaultParser();
    CommandLine cmd = parser.parse(options, args);

    if(cmd.hasOption("x")) {
      System.out.println("Hi!");
    }
  }
}
