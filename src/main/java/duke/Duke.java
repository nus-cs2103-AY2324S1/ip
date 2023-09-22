package duke;

import java.util.ArrayList;

import commands.Command;
import data.Actions;
import data.Save;
import parser.Parser;
import tasks.Task;
import ui.UI;

/**
 * Represents the main chatbot. Manages the user interface, task list, command parsing,
 * and saving/loading tasks to/from a file.
 */
public class Duke {
    private final UI ui = new UI();
    private final Actions actionList = new Actions();
    private final Parser parser = new Parser();
    private final Save savior = new Save();

    /**
     * Constructs a Duke chatbot instance.
     * On instantiation, is tasks exist in the duke.txt file, they are loaded.
     */
    public Duke() {
        ArrayList<Task> loadedTasks = savior.loadTasks();
        actionList.add(loadedTasks);
    }

    /**
     * Initiates the chatbot interaction.
     * Reads and executes commands continuously until the user requests to exit (ByeCommand).
     * After exiting, all tasks are saved to duke.txt.
     *
     * @throws DukeException If any command-related errors occur.
     */
    public void initiateChatbot() throws DukeException {
        ui.openingMessage();
        String input = ui.readInput();
        Command command = parser.issueCommand(input);
        while (!command.isExit()) {
            try {
                command.executeCommand(ui, actionList);
            } catch (DukeException ohno) {
                ui.lineSandwich(ohno.getMessage());
            }
            input = ui.readInput();
            command = parser.issueCommand(input);
        }
        savior.saveTasks(actionList.list());
    }

    /**
     * The entry point for the chatbot.
     *
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        try {
            duke.initiateChatbot();
        } catch (DukeException duked) {
            System.out.println("An error occurred: " + duked.getMessage());
        }
    }
}
