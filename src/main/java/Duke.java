/**
 * Chatbot implementation
 */
public class Duke {
    // Chatbot name
    static final String NAME = "Atlas";
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;


    /**
     * Constructs a Duke object
     * @param filePath Relative path to save task list
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
        ui = new Ui();
        parser = new Parser();
    }

    /**
     * Constructs a new Duke instance and runs it
     * @param args Command-line arguments passed in at startup
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

    /**
     * Runs the chatbot, listens to user inputs, and executes commands
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command c = parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    /**
     * Exception class for exceptions that terminate the command and meant to be handled by Duke
     */
    public static class DukeException extends RuntimeException {

        /**
         * Constructs a DukeException exception
         * @param errorDetails String containing information about error
         */
        public DukeException(String errorDetails) {
            super(errorDetails);
        }

        @Override
        public String getMessage() {
            return "Sorry, I ran into an error! Here's more info:\n" + super.getMessage();
        }
    }
}
