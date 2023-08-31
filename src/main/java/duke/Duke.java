package duke;

/**
 * The Duke class represents a simple task management application.
 * It initializes necessary components and controls the main application flow.
 */
public class Duke {
    private final Parser parser;
    private final TaskList list;
    private final Ui ui;

    /**
     * Constructs a Duke object with the specified file path for task storage.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.list = new TaskList(filePath);
        this.parser = new Parser(this.list, this.ui);
    }

    /**
     * Runs the main loop of the Duke application.
     * Greets the user, reads and parses commands, and executes corresponding actions.
     */
    public void run() {
        boolean shouldContinue = true;
        this.ui.greet();
        while (shouldContinue) {
            try {
                String input = this.ui.readCommand();
                this.parser.parse(input);
                shouldContinue = this.parser.getStatus();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * The main method to start the Duke application.
     *
     * @param args Command-line arguments (not used in this context).
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
