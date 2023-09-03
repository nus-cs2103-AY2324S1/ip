package duke;

import duke.commands.Command;
import duke.exceptions.CommandDetailException;
import duke.exceptions.CommandNotRecognizedException;
import duke.exceptions.StorageException;
import duke.exceptions.TimeParsingException;
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
 * Represents the main class for the Duke application.
 * Responsible for initializing the application and handling the user input.
 */
public class Duke extends Application {

    private static final String DATA_FILE_PATH = "./data/duke.txt";
    private static final Storage storage = new Storage(DATA_FILE_PATH);
    private static final Ui ui = new Ui();
    private static final Parser parser = new Parser();


    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * The main entry point for the Duke application.
     * Initializes the user interface, loads stored tasks from storage,
     * and then continuously waits for user input until the exit command is received.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {


        ui.showLine();
        ui.showWelcome();
        TaskList tasks;
        try {
            tasks = storage.load();

            boolean isExit = false;
            while (!isExit) {
                try {
                    String input = ui.readCommand();
                    ui.showLine();
                    Command c = parser.parse(input);
                    c.execute(tasks, ui, storage);
                    isExit = c.isExit();
                } catch (CommandNotRecognizedException e) {
                    ui.showError(e);
                    ui.showCommandNotRecognized();
                } catch (CommandDetailException e) {
                    ui.showError(e);
                    ui.showNoCommandDetail();
                } catch (TimeParsingException e) {
                    ui.showError(e);
                    ui.showTimeParsingError();
                } catch (StorageException e) {
                    ui.showError(e);
                    ui.showLoadingError();
                } finally {
                    ui.showLine();
                }
            }
        } catch (StorageException e) {
            ui.showLoadingError();
        } finally {
            ui.showLine();
        }
    }

    /**
     * Starts the Duke application.
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     */
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
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // more code to be added here later
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, new ImageView(user)),
            DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
