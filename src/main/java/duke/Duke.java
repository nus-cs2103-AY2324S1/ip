package duke;

import duke.utilities.Input;
import duke.utilities.Parser;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main class for Duke Chatbot
 */
public class Duke extends Application{

	private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /** Variable to store task list */
    private Storage storage;

    /** Variable to handle list of tasks operations */
    private TaskList tasks;

    /** Variable to handle user interactions */
    private Ui ui;

    /** Variable to handle user inputs */
    private Parser parser;

    /**
     * Creates a new instance of Duke chatbot
     *
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        if (storage.fileExists()) {
            tasks = new TaskList(storage.loadTasksData());
        } else {
            tasks = new TaskList();
        }
    }

    /**
     * Starts the execution of the chatbot
     */
    public void run() {
        ui.greet();
        boolean endSession = true;
        while (endSession) {
            String userInput = ui.startInputSession();
            Input parsedInput = parser.parse(userInput);
            endSession = ui.handleInput(tasks, parsedInput, parser);
            tasks.overwriteTasksData(storage);
        }
    }

    public static void main(String[] args) {
        new Duke("./tasks.txt").run();
    }

	@Override
	public void start(Stage stage) {
		//Step 1. Setting up required components

        //The container for the content of the chat to scroll.
		scrollPane = new ScrollPane();
		dialogContainer = new VBox();
		scrollPane.setContent(dialogContainer);

		userInput = new TextField();
		sendButton = new Button("Send");

		AnchorPane mainLayout = new AnchorPane();
		mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

		scene = new Scene(mainLayout);

		stage.setScene(scene);
		stage.show();

	   // more code to be added here later
	}
}
