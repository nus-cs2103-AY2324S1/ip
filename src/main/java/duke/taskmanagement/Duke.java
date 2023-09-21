package duke.taskmanagement;

import javafx.stage.Stage;

/**
 * Duke is a chatbot application that manages tasks. It interacts with the user
 * to create, read, update, and delete tasks, and it stores task data in a file.
 */
public class Duke {
    String PATH = "./data/duke.txt";
    private Ui ui = new Ui();
    private TaskList tasks;
    private Storage storage = new Storage(PATH);
    private Stage stage;

    /**
     * Constructor for the Duke class.
     *
     * @param stage The JavaFX stage for the graphical user interface.
     */
    public Duke (Stage stage) {
        this.stage = stage;
        tasks = new TaskList(this.ui, storage.loadData(), storage);
        assert this.storage != null : "taskManager of Duke instance should not be null";
    }

    /**
     * Return a String that contains the intended message
     * after getting user's input
     *
     * @param input The input from user.
     * @return String message to the user.
     */
    public String getResponse(String input) {

        String greetingMessage = ui.greet();
        assert greetingMessage == "Hello! I'm JJ\n" +
                "What can I do for you?\n" + "\n" : "greeting should contain default greet";
        Parser parser = new Parser(this.ui, this.tasks);
        assert parser != null : "parser should not be null at any point";
            String messageToUser = parser.readCmd(this.tasks, input);
            return messageToUser;
    }

    /**
     * Closes the application by closing the JavaFX stage.
     */
    public void close() {
        stage.close();
    }

    /**
     * The main method that launches the Duke application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Launcher.main(args);
    }
}
