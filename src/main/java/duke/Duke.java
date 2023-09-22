package duke;

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

import java.util.ArrayList;
import java.util.Scanner;


/**
 * The `Duke` class extends `Application` and serves as the central
 * component for handling user input and providing output through a GUI.
 * It manages a chat interface for interactions with the user.
 * Credit to: https://se-education.org/guides/tutorials/javaFx.html
 */
public class Duke extends Application {

    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    String saveFileDir = "./data/duke.txt";
    UiGUI UiGUI = new UiGUI();
    Storage storage = new Storage(saveFileDir);
    TaskList tasks = new TaskList(storage.loadSaveFile());
    ParserGUI parserGUI = new ParserGUI(tasks, UiGUI, storage);
    ArrayList<Task> myList = new ArrayList<Task>(); // Create an ArrayList object
    Scanner myScanner = new Scanner(System.in);
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Starts the Duke application and initializes the GUI components.
     *
     * @param stage The primary stage for the application.
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

        // more code to be added here later

        //Step 2. Formatting the window to look as expected
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

        Label dukeText = new Label(UiGUI.hello());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(dukeText, new ImageView(duke)));
        //Automatically remind for the next 3 days
        dukeText = new Label(UiGUI.remind(tasks, 3));
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(dukeText, new ImageView(duke)));
    }


    /**
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
     * Handles user input by displaying it in the chat interface and obtaining
     * a response from the Duke chatbot.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(userText, new ImageView(user)), DialogBox.getDukeDialog(dukeText, new ImageView(duke)));
        userInput.clear();
    }

    /**
     * Obtains a response from the Duke chatbot based on user input.
     *
     * @param input The user's input.
     * @return The Duke chatbot's response.
     */
    private String getResponse(String input) {
        return parserGUI.parseInput(input);
    }


}