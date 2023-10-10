package duke;

/**
 * The Duke class represents a task management application that allows users to interact with tasks.
 * It provides methods to manage tasks, mark them as done, and perform various operations on tasks.
 */
public class Duke {
    private static final String FILE_PATH = "./src/main/data/tasklist.txt";
    private TaskList tasks;

    /**
     * Constructs a Duke instance with the specified file path to load task data from.
     */
    public Duke() {
        Storage storage = new Storage(FILE_PATH);
        this.tasks = new TaskList(storage);
        try {
            storage.loadTasks();
        } catch (DukeException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    /**
     * Processes user input to generate a response based on the provided task list.
     *
     * @param userInput The user's input to be processed.
     * @return A response generated by parsing the user input with the given task list.
     */
    public String getResponse(String userInput) {
        Parser parser = new Parser();
        parser.setUserInput(userInput);
        return parser.parse(this.tasks);
    }
}
