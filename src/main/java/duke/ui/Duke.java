package duke.ui;

import duke.exceptions.DukeException;
import duke.exceptions.StorageException;
import duke.filehandler.Storage;
import duke.parsers.InputParser;
import duke.tasks.TaskList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Duke extends Application {
    private static TaskList taskList;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Storage storage;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/little-asian-boy-surprised-1432293.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/puppy-1371458.jpg"));
    private InputParser parser;

    public Duke() {
    }

    /**
     * Gets tasks from stored text file, stores tasks in taskList,
     * saves taskList to inputParser object
     *
     * @param filePath
     * @throws StorageException
     */
    public void retrieveFromStorage(String filePath) throws StorageException {

        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.readTasks());
        } catch (DukeException e) {
            throw new StorageException(e.getMessage());
        }
        this.parser = new InputParser(taskList);
    }

    /**
     * Sets up main window
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     */
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

        stage.setTitle("duke.ui.Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(600.0);

        mainLayout.setPrefSize(600.0, 600.0);

        scrollPane.setPrefSize(585, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(525.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Retrieve existing tasks from storage, display welcome message
        try {
            retrieveFromStorage("./samData/tasks.txt");
            Label success = new Label(Ui.greetingText());
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(success, new ImageView(duke)));
        } catch (StorageException e) {
            Label error = new Label(e.getMessage());
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(error, new ImageView(duke)));
        }

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing duke.ui.Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        userText.setPadding(new Insets(5));
        String dukeReplyStr = getResponse(userInput.getText());
        //If user types "bye", programme will quit
        if (dukeReplyStr.equals("exit app")) {
            Label end = new Label(Ui.endingText());
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(end, new ImageView(duke)));
            Platform.exit();
        }
        Label dukeText = new Label(dukeReplyStr);
        dukeText.setPadding(new Insets(5));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Sends user input to parser file
     *
     * @param input
     * @return parsed String
     */
    private String getResponse(String input) {
        return parser.parse(input, true);
    }
}
