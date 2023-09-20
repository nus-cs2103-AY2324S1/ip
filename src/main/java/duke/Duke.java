package duke;

/**
 * The Duke class represents a simple chatbot application that helps manage tasks.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a new Duke chatbot with the provided file path.
     */
    public Duke() {
        storage = new Storage("./data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response to user input.
     *
     * @param input The user's input.
     * @return A response generated based on the user's input.
     */
    public String getResponse(String input) {
        StringBuilder result = new StringBuilder();
        try {
            result.append(Parser.executeCommand(input, tasks));
            storage.save(tasks);
        } catch (DukeException e) {
            result.append(e.getMessage());
        }
        return result.toString();
    }
}
