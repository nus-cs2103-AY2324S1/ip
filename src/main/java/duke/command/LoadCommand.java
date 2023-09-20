package duke.command;

import java.io.IOException;

import duke.TaskList;
import duke.storage.Storage;


/**
 * Represents a command to load from a specified file.
 * Note that this command is not explicitly used by the user, but called once at the start of the program.
 */
public class LoadCommand extends Command {
    public static final String COMMAND_WORD = "load";
    public static final String MESSAGE_USAGE = "load <file path>";
    public static final String MESSAGE_EMPTY_FILE_PATH = "Sorry, but the file path cannot be empty.";
    private final String fileToLoad;

    public LoadCommand(String fileToLoad) {
        this.fileToLoad = fileToLoad;
    }

    /**
     * Assigns a new storage object given the file to be used as data.
     *
     * @return the response to the user
     */
    public String[] execute() {
        Storage storage = new Storage(fileToLoad);
        this.duke.setStorage(storage);
        try {
            TaskList tasks = storage.load();
            this.duke.setTaskList(tasks);
            if (tasks.size() == 0) {
                return new String[]{String.format(
                        "No stored tasks found from %s", fileToLoad),
                        "Loading an empty task list."
                };
            } else {
                return new String[]{String.format(
                        "%s task%s loaded from %s",
                        tasks.size(),
                        tasks.size() == 1 ? "" : "s",
                        fileToLoad
                )};
            }
        } catch (IOException e) {
            this.duke.setTaskList(new TaskList());
            return new String[]{
                    String.format("Error loading tasks from %s", fileToLoad),
                    "Starting from an empty task list."
            };
        }
    }
}
