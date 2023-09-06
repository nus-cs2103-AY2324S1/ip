package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Duke is a simple task management application that allows users to keep track
 * of tasks, including to-dos, deadlines, and events.
 *
 * Duke uses a command-based approach, where users enter text commands to
 * perform various operations on the task list.
 */
public class Duke extends Application {

    private static final String DEFAULT_FILE_PATH = "src/data/duke.txt";
    private Image user = new Image(this.getClass().getResourceAsStream("/images/Darwin.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Gumball.png"));
    private Storage storage;
    private TaskList items;
    private Ui ui;


    /**
     * Constructs a Duke instance.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            items = new TaskList(storage.load());
        } catch (DukeException c) {
            ui.showLoadingError();
            items = new TaskList();
        }
    }

    public Duke() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Runs the Duke application.
     *
     * This method initializes the user interface, loads tasks from storage,
     * and enters a loop to process user commands.
     */
    public void run() {
        ui.greet();

        boolean isRunning = true;
        while (isRunning) {
            try {
                String fullCommand = ui.getInput();
                Command c = Parser.parse(fullCommand);
                c.execute(items, ui, storage);
                isRunning = !c.willExitNext();
            } catch (DukeException e) {
                ui.talk(e.getMessage());
            }
        }
    }

    @Override
    public void start(Stage stage) {
        //The container for the content of the chat to scroll.
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // Formatting the window to look as expected
        stage.setTitle("JED");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(65.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(dialogContainer, userInput);
        });
        userInput.setOnAction((event) -> {
            handleUserInput(dialogContainer, userInput);
        });

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput(VBox dialogContainer, TextField userInput) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Returns Duke's response given the input.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }



    /**
     * The main method that starts the Duke application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Duke("src/data/duke.txt").run();
    }
}
