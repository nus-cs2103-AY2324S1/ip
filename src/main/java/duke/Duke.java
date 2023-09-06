package duke;

import javafx.application.Application;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public Duke() {

    }

    /**
     * Runs the program.
     */
    public void run() {
        Ui.greetUser();
        Scanner scanObj = new Scanner(System.in);
        boolean isRunning = true;
        while(isRunning) {
            String nextLine = scanObj.nextLine();
            isRunning = Parser.parseCommands(nextLine, this.tasks, this.storage);
        }
    }

    /**
     * Creates a new instance of CringeBot.
     *
     * @param args Command line args.
     */
    public static void main(String[] args) {
        new Duke("./data/data.ser").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return input;
    }
}
