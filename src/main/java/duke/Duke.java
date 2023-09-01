package duke;

import duke.task.DukeList;

/**
 * Represents the main class for the Duke application.
 * Initializes and coordinates various components of the application.
 */
public class Duke {
    private DukeList dukelist;
    private Ui ui;
    private Storage storage;
    private static final String FILE_PATH = "data/duke.txt";

    /**
     * Constructs a Duke object with the specified file path.
     *
     * @param filePath The path to the data file for storage.
     */
    public Duke(String filePath) {
        ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.dukelist = new DukeList(this.storage.getData());
        } catch (DukeException e) {
            this.dukelist = new DukeList();
            System.out.println("error");
        }
    }

    /**
     * The main method that initializes the Duke application.
     *
     * @param args Command-line arguments.
     * @throws DukeException If there's an exception during initialization or execution.
     */
    public static void main(String[] args) throws DukeException {
        new Duke(FILE_PATH).froggie();
    }

    /**
     * Initiates the Duke application by displaying a greeting and starting the parsing process.
     */
    public void froggie() {
        this.ui.hello();
        Parser parser = new Parser();
        parser.parse(this.storage, dukelist, ui);
    }
}
