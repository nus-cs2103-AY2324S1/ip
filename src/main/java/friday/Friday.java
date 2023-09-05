package friday;

import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

/**
 * Represents the main class for the Friday application.
 */
public class Friday extends Application {
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image friday = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private TaskList taskList;
    private Storage storage;
    private Scanner input;
    private Ui ui;
    private Parser parser;
    private TextField userInput;

    private VBox dialogContainer;

    /**
     * Constructs a new instance of the Friday application.
     * Initializes the user interface, task list, input scanner, storage, and parser.
     */
    public Friday() {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.input = new Scanner(System.in);
        this.storage = new Storage("data/tasks.txt");
        this.parser = new Parser();
        this.userInput = new TextField();
        this.dialogContainer = new VBox();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Step 1. Setting up required components
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(dialogContainer);

        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // Step 2. Formatting the window
        stage.setTitle("Friday");
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

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            handleUserInput();
        });
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Friday's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getFridayDialog(userInput.getText(), friday)
        );
        userInput.clear();
    }

    /**
     * Generates a response based on user input.
     * @param input The user's input string.
     * @return A response string.
     */
    public String getResponse(String input) {
        input = input.toLowerCase().trim();

        if (input.equals("hello") || input.equals("hi") || input.equals("hey")) {
            return "Hello! How can I assist you today?";
        }


        if (input.contains("thank")) {
            return "You're welcome!";
        }

        if (input.contains("what can you do")) {
            return "I can manage tasks for you! You can add tasks, mark them as done, or delete them.";
        }

        if (input.contains("bye") || input.contains("goodbye")) {
            return "Goodbye! If you need anything else, just let me know.";
        }

        return "I'm sorry, I didn't quite catch that. Can you rephrase or ask something else?";
    }
}
