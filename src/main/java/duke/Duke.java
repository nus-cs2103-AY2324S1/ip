package duke;

import duke.task.DukeList;

/**
 * Duke is a simple task management application.
 * It allows users to add, delete, mark tasks as done, and list tasks.
 * The tasks can be stored in a file for persistence.
 */
public class Duke {

    /** The DukeList that stores and manages tasks. */
    private DukeList dukelist;

    /** The user interface for interacting with Duke. */
    private Ui ui;

    /** The storage component for reading and writing tasks to a file. */
    private Storage storage;

    /** The file path where tasks are stored. */
    private static final String FILE_PATH = "data/duke.txt";

    /**
     * Constructs a Duke instance with the specified file path.
     *
     * @param filePath The file path to read and store tasks.
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
     * The main entry point of the Duke application.
     *
     * @param args The command-line arguments.
     * @throws DukeException If an error occurs while running Duke.
     */
    public static void main(String[] args) throws DukeException {
        new Duke(FILE_PATH).froggie();
    }

    /**
     * Initializes Duke, displays a welcome message, and starts the main interaction loop.
     */
    public void froggie() {
        this.ui.hello();
        Parser parser = new Parser();
        parser.parse(this.storage, dukelist, ui);
    }
}
