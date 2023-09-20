package dook;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import dook.command.Command;
import dook.services.Parser;
import dook.services.Storage;
import dook.services.TaskList;

/**
 *  Class containing the main execution function.
 */
public class Dook {
    public static final Path TASKLIST_PATH = Paths.get("dook.txt");

    public static final Path ALIASES_PATH = Paths.get("commandAliases.txt");
    private final Storage storage;
    private final Parser parser;
    private TaskList taskList = new TaskList(null);

    private boolean isExit = false;

    /**
     * Assigns the proper file path to Storage.
     */
    public Dook() {
        this.storage = new Storage(TASKLIST_PATH, ALIASES_PATH);
        this.parser = new Parser();
    }

    public boolean getIsExit() {
        return this.isExit;
    }

    /**
     * Sets up the task list from the saved list.
     * @return The saved task list, or an error message otherwise.
     */
    public String initialise() {
        parser.initialise();
        try {
            storage.loadAliasesFromFile(parser);
        } catch (DookException d) {
            return "Failed to read saved aliases";
        }

        try {
            taskList = new TaskList(storage.loadTasksFromFile());
            return taskList.toString();
        } catch (DookException d) {
            taskList = new TaskList(new ArrayList<>());
            return "Failed to read saved list";
        }
    }

    public String getResponse(String input) {
        try {
            Command c = parser.parseFullInput(input);
            if (c.getIsExit()) {
                this.isExit = true;
            }
            return c.execute(storage, taskList, parser);
        } catch (DookException e) {
            return e.getMessage();
        }
    }
}
