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
 * and saving/loading tasks to/from duke.txt.
 */
public class Duke {
    private final UI ui = new UI();
    private final Actions actionList = new Actions();
    private final Parser parser = new Parser();
    private final Save savior = new Save();

    /**
     * Constructs a Duke chatbot instance.
     * On instantiation loads tasks from duke.txt (if it exists).
     */
    public Duke() {
        ArrayList<Task> loadedTasks = savior.loadTasks();
        assert loadedTasks != null;
        actionList.add(loadedTasks);
    }

    /**
     * Processes user input and returns a response from the chatbot.
     *
     * @param input The input string from the user.
     * @return The chatbot's response as a string.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.issueCommand(input);
            if (command.isExit()) {
                savior.saveTasks(actionList.list());
                return " Finally, I can rest.";
            } else {
                return command.executeCommand(ui, actionList);
            }
        } catch (DukeException ohno) {
            return ohno.getMessage();
        }
    }
}
