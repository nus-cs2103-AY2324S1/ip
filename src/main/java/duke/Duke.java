package duke;

/**
 * A chatbot that helps a person to keep track of a list of tasks.
 *
 * @author Qin Yan Er
 */
public class Duke {

    private static final String FILE_PATH = "./data/main.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Creates a new duke.Duke instance.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println("Loading Error");
            tasks = new TaskList();
        }
    }


    /**
     * Generates a response to user input.
     *
     * @param input The user's input command.
     * @return A string containing the response to the user's input.
     */
    public String getResponse(String input) {
        try {
            assert !input.isEmpty() : "Input cannot be empty.";

            return Parser.parse(input, tasks, storage, ui);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
