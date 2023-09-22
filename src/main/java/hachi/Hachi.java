package hachi;

import hachi.exceptions.HachiException;

/**
 * A task list app that allows the user to add Todos, Deadlines, and Events.
 * The app saves the list locally on the computer, and thus can remember tasks entered previously.
 * The user can add tasks, delete tasks, mark tasks as done, list all tasks, and search for them by date and name.
 */
public class Hachi {

    private static final String DEFAULT_TASK_PATH = "./data/tasks.txt";

    private final Storage storage;

    private final String filePath;

    /**
     * Overloaded constructor for the Hachi class.
     * Initialises the UI, Storage, and uses the default filePath to store the tasks at.
     */
    public Hachi() {
        storage = new Storage();
        filePath = DEFAULT_TASK_PATH;
    }

    /**
     * Overloaded constructor for the Hachi class.
     * Initialises the UI, Storage, and takes in a filePath to store the tasks at.
     * @param filePath The relative location to store the text file for the tasks in.
     */
    public Hachi(String filePath) {
        storage = new Storage();
        this.filePath = filePath;
    }

    /**
     * Returns the appropriate response for the user input.
     * @throws HachiException The appropriate exception when one does appear
     */
    public String getInputResponse(String input) throws HachiException {
        TaskList taskList = storage.getTaskList();
        Command cmd = Parser.parse(input, taskList, storage);
        return cmd.execute();
    }

}
