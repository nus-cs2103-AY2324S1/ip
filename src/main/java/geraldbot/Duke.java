package geraldbot;

import java.util.ArrayList;

import geraldbot.exception.DukeException;
import geraldbot.task.Task;
import geraldbot.util.Parser;
import geraldbot.util.Storage;
import geraldbot.util.Ui;

/**
 * The main class that represents the Duke chatbot application.
 * Duke is a task manager that can handle various commands to manage tasks.
 */
public class Duke {
    private final Parser parser;
    private final Ui ui;

    /**
     * Constructs a Duke object and initializes the user interface and parser.
     * Reads task data from storage and initializes the task list.
     */
    public Duke() {
        Storage storage = new Storage("./data/data.txt");
        ArrayList<Task> taskList = storage.read();
        this.ui = new Ui();
        this.parser = new Parser(storage, taskList);
    }

    /**
     * Main method to start the Duke chatbot application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.run();
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

    /**
     * Runs the main loop of the Duke application.
     * The loop repeatedly reads user input, processes it using the parser,
     * and displays the corresponding output or error messages.
     */
    public void run() {
        ui.showLine();
        ui.greet();
        ui.showLine();

        while (true) {
            try {
                String input = ui.readInput();
                if (input.equals("bye")) {
                    ui.showLine();
                    ui.bye();
                    ui.showLine();
                    break;
                } else {
                    ui.showLine();
                    parser.parse(input);
                    ui.showLine();
                }
            } catch (DukeException e) {
                System.out.println(e);
                ui.showLine();
            }
        }
    }
}
