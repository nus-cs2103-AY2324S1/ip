package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Represents the main class for the Duke application.
 * It initializes the UI, storage, and task list, and then runs the application.
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
    private boolean isExit = false;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructs a Duke object.
     *
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/duke.txt");

        try {
            tasks = new TaskList(storage.load());
            assert tasks != null : "tasks should be initialized"; // tasks should not be null
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
        ui.printWelcome();
    }

    /**
     * Initializes the primary stage of the Duke application's GUI.
     * Sets up the layout, user input handling, and display properties.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {

        //Step 1. Formatting the window to look as expected.
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

        //Step 2. Formatting the window to look as expected
        stage.setTitle("MAX");
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);



        //Step 3. Add functionality to handle user input.
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
     * Creates a label with the specified text.
     *
     * @param text The text to be displayed in the label.
     * @return A label with the specified text with word wrapping enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Handles user input from the GUI.
     * Processes the input command and displays the corresponding output in the dialog container.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        assert userText != null : "userText should be initialized"; // userText should not be null
        Label dukeText = new Label(getResponse(userInput.getText()));
        assert dukeText != null : "dukeText should be initialized"; // dukeText should not be null
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        if (this.isExit) {
            System.exit(0);
        }
        userInput.clear();
    }

    /**
     * Generates a response based on the user's input.
     *
     * @param input The user's input command.
     * @return A string response based on the processed command.
     */
    private String getResponse(String input) {
        if (isExit) {
            return "Bye. Hope to see you again soon!";
        }
        try {
            ui.clearMessage();
            Parser parser = new Parser();
            parser.parseAndExecute(input, tasks, ui, storage);
            this.isExit = parser.isExit();
            return ui.getMessage();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}

