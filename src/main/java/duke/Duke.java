package duke;

import java.util.Scanner;

/**
 * Represents a chat bot that helps the user to keep track of tasks.
 */
public class Duke {

    /**
     * Runs the program until the user enters "bye".
     */
    public static void runProgram() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            Parser.parseCommands(input);
            input = sc.nextLine();
        }
        sc.close();
    }

    /**
     * Greets the user and runs the program.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Ui.greet();
        runProgram();
        Ui.exit();
    }
}
