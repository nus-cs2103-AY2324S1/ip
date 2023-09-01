package duke;

import java.util.Scanner;

/**
 * The Duke class represents the chatbot.
 */
public class Duke {
    /** Stores file information and handles the file operations **/
    private Storage storage;
    /** Stores the list of tasks **/
    private TaskList tasks;
    /** Instance of Ui to handle user interactions **/
    private Ui ui;

    /**
     * Instantiates an instance of Duke.
     *
     * @param filePath String of the intended file path.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Runs the program.
     */
    public void run() {
        Ui.start();
        Scanner scan = new Scanner(System.in);
        String input = Ui.getInput(scan);
        while (!input.equals("bye")) {
            Parser.parseInput(input, this.tasks, this.storage);
            input = Ui.getInput(scan);
        }
        Ui.bye();
    }

    public static void main(String[] args) {
        new Duke("./duke.txt").run();
    }
}