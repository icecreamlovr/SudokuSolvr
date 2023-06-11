## Notes
The server is now hosted on [AWS](http://184.169.222.204/).

**I've moved the project to a private repository. Please contact me if you need help with anything.**

# SudokuSolvr by Yue He

### Set up the SudokuSolvr server
Make sure you have Java 17, Gradle and Node.js installed.

To launch the server, run:

```bash
$ gradle bootRun
```

This will run the npm install and webpack build, then launch the Spring Boot server.

Then, go to http://localhost:8080/ to see the web page.

![screenshot](screenshots/SudokuSolver_screenshot.png?raw=true "server screenshot")

### Deploy to a remote host and serve

First package the application (Backend + Frontend):

```bash
$ gradle bootJar
```

This should create a JAR file at `./build/libs/SudokuSolvrServer-1.0-SNAPSHOT.jar`.

Scp the JAR file to a remote host. Then start the server (in detached mode) with:

```bash
$ nohup java -jar ~/SudokuSolvrServer-1.0-SNAPSHOT.jar &
```

### To serve on port :80 instead of :8080

DO NOT modify the serving port in application.properties to :80.
- Reason: Linux requires `ROOT` privilege for process to listen on :80. This causes 2 problems
  - Deployment is complicated. Need `sudo -b`. Also host needs to support sudo.
  - Potential vulnerability: since server is run in root privilege, if it's attacked (e.g. injection), attacker will also be run things in root privilege.

Instead, package and deploy the server as is. Then setup port forwarding in the remote host:

```bash
$ sudo iptables -t nat -A PREROUTING -p tcp --dport 80 -j REDIRECT --to-ports 8080
```

### Use the command line tool
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
