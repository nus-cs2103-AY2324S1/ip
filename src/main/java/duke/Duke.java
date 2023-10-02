package duke;

import duke.command.Command;
import duke.gui.DialogBox;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * All the sourcecode behind the chatbot, Duke
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructs a Duke instance with the specified file path.
     */
    public Duke() {
        String filePath = "./data/duke.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke chatbot, allowing user interaction.
     * Displays a welcome message and processes user commands until the user exits.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Initializes the JavaFX application and sets up the main user interface.
     *
     * @param stage The primary stage for the JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        // Step 1: Setting up required components
        scrollPane = createScrollPane();
        dialogContainer = new VBox();
        userInput = new TextField();
        sendButton = createSendButton();

        AnchorPane mainLayout = createMainLayout();

        // Step 2: Formatting the window to look as expected
        formatStage(stage);
        Scene scene = new Scene(mainLayout, 400.0, 600.0);
        stage.setScene(scene);
        stage.show();

        // Part 3: Add functionality to handle user input
        setupUserInputHandlers();
    }

    /**
     * Creates and configures a ScrollPane for displaying the conversation.
     *
     * @return The configured ScrollPane.
     */
    private ScrollPane createScrollPane() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(dialogContainer);
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        return scrollPane;
    }

    /**
     * Creates a "Send" button for sending user input.
     *
     * @return The "Send" button.
     */
    private Button createSendButton() {
        Button sendButton = new Button("Send");
        sendButton.setPrefWidth(55.0);
        return sendButton;
    }

    /**
     * Creates the main layout of the user interface.
     *
     * @return The AnchorPane containing the main layout.
     */
    private AnchorPane createMainLayout() {
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(400.0, 600.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        return mainLayout;
    }

    /**
     * Formats the primary stage with desired properties.
     *
     * @param stage The primary stage for the JavaFX application.
     */
    private void formatStage(Stage stage) {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }

    /**
     * Sets up event handlers for user input.
     */
    private void setupUserInputHandlers() {
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }


    /**
     * The start method for the Duke chatbot.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                DialogBox.getDukeDialog(response, duke)
        );
        userInput.clear();
    }

    /**
     * Generates a response to user input.
     *
     * @param input The user input to process.
     * @return A response to the user's input.
     */
    public String getResponse(String input) {
        try {
            String fullCommand = input;
            ui.showLine(); // Show the divider line ("_______")
            Command c = Parser.parse(fullCommand);
            String output = c.execute(tasks, ui, storage);
            ui.showLine(); // Show the divider line again
            return output;
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
