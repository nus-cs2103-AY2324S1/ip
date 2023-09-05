package geraldbot;

import java.util.ArrayList;

import geraldbot.exception.DukeException;
import geraldbot.task.Task;
import geraldbot.util.Parser;
import geraldbot.util.Storage;

/**
 * The main class that represents the Duke chatbot application.
 * Duke is a task manager that can handle various commands to manage tasks.
 */
public class Duke {
    private final Parser parser;

    /**
     * Constructs a Duke object and initializes the user interface and parser.
     * Reads task data from storage and initializes the task list.
     */
    public Duke() {
        Storage storage = new Storage("./data/data.txt");
        ArrayList<Task> taskList = storage.read();
        this.parser = new Parser(storage, taskList);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            String response = parser.parse(input);
            return response;
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
