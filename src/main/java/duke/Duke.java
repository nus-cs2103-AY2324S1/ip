package duke;

/**
 * Duke is a simple task management program that allows users to add, delete, mark, and list tasks.
 */
public class Duke {
    private static TaskList taskList;
    private static Parser parser;

    public Duke() {
        final String DATA_DIRECTORY = "data";
        String projectRoot = System.getProperty("user.dir");
        String dataFilePath = projectRoot + "/" + DATA_DIRECTORY + "/tasks.ser";
        taskList = new TaskList(dataFilePath);

        parser = new Parser(taskList);
    }
    
    public String getResponse(String input) {
        try {
            Executable executable = parser.parseCommand(input);
            return executable.execute();
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}
