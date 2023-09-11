package james;

import java.util.Scanner;

/**
 * Represents the user interface of the program.
 */
public class Ui {

    private static String line = "_________________________";

    /**
     * The parser used to parse user input.
     */
    private Parser parser;


    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.parser = new Parser();
    }

    /**
     * Prints the error message when the file cannot be loaded.
     */
    public void showLoadingError() {
        System.out.println("Error loading file");
    }

    /**
     * Starts the program and takes in user input.
     *
     * @param tasks The list of tasks.
     */
    public void start(TaskList tasks) {
        System.out.println(line + "\n" + "Hello! I'm James\n + What can I do for you?\n" + line);

        Scanner in = new Scanner(System.in);

        String input = in.nextLine();
        while (!input.equals("bye")) {
            String output = this.parser.parse(tasks, input);
            System.out.println(output);
            System.out.println(line);
            input = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n" + line);
    }

    public String processInput(TaskList tasks, String input) {
        return this.parser.parse(tasks, input);
    }
}
