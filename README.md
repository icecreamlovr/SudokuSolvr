## Notes
The server is now hosted on [AWS](http://184.169.222.204:8080/).

I've moved the project to a private repository. Please contact me if you need help with anything!

# SudokuSolvr by Yue He

### To setup the server
Make sure you have Java 17, Gradle and Node.js installed.

To launch the server, run:

```bash
gradle bootRun
```

This will run the npm install and webpack build, then launch the Spring Boot server.

Then, go to http://localhost:8080/ to see the web page.

### To run the command line tool
Make sure you have Java and Gradle installed.

To run the command line tool, run:

```bash
gradle sudokuSolvrCli --args="--input='[
       [5, 3, 0, 0, 7, 0, 0, 0, 0],
       [6, 0, 0, 1, 9, 5, 0, 0, 0],
       [0, 9, 8, 0, 0, 0, 0, 6, 0],
       [8, 0, 0, 0, 6, 0, 0, 0, 3],
       [4, 0, 0, 8, 0, 3, 0, 0, 1],
       [7, 0, 0, 0, 2, 0, 0, 0, 6],
       [0, 6, 0, 0, 0, 0, 2, 8, 0],
       [0, 0, 0, 4, 1, 9, 0, 0, 5],
       [0, 0, 0, 0, 8, 0, 0, 7, 9]]'"
```
