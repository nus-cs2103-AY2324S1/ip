package duke;

/**
 * Duke is a simple task management program that allows users to add, delete, mark, and list tasks.
 */
public class Duke {
    private static TaskList taskList;
    private static Parser parser;

    /**
     * Constructs a Duke instance.
     * Initializes the task list and parser.
     */
    public Duke() {
        final String dataDirectory = "data";
        String projectRoot = System.getProperty("user.dir");
        String dataFilePath = projectRoot + "/" + dataDirectory + "/tasks.ser";
        taskList = new TaskList(dataFilePath);

        parser = new Parser(taskList);
    }

    /**
     * Gets a response based on the user input.
     *
     * @param input The user input to process.
     * @return A response message based on the input.
     */
    public String getResponse(String input) {
        try {
            Executable executable = parser.parseCommand(input);
            return executable.execute();
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}
