package Duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * The Ui class represents the user interface for the Duke program.
 * It handles user input and displays messages to the user.
 */
public class Ui {

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
    }
    private Parser parser = new Parser();

    /**
     * Gets user input from the command line and processes it using a Parser object.
     * @param tasks the TaskList object to store and manage tasks
     * @param storage the Storage object to save and load tasks
     */
    public String getInput(TaskList tasks, Storage storage, String input) {
        while (!input.equals("bye")) {
            try {
                return parser.parse(input, tasks);
            } catch (DukeException e) {
                return e.getMessage();

            }
        }
        try {
            storage.saveTasks(tasks.taskList);
        } catch (IOException e) {
            return e.getMessage();
        }
        return "Bye! Auntie maggie see you later!";
    }
}


