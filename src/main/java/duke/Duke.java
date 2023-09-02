package duke;

import duke.ui.Ui;

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
            System.out.println(Parser.parseCommands(input));
            input = sc.nextLine();
        }
        sc.close();
    }

    public String getResponse(String input) {
        return Parser.parseCommands(input);
    }

    /**
     * Greets the user and runs the program.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        System.out.println(Ui.greet());
        runProgram();
        System.out.println(Ui.exit());
    }
}
