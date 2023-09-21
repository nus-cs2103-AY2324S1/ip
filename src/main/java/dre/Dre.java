package dre;

import dre.exception.DreException;
import dre.gui.DialogBox;
import dre.ui.Ui;
import dre.storage.Storage;
import dre.parser.Parser;
import dre.task.TaskList;
import dre.command.Command;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Represents the main application class, serving as the primary entry point
 * to initiate and run the Dre task management system.
 */
public class Dre extends Application {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private final Image dre = new Image(this.getClass().getResourceAsStream("/images/DaDre.jpg"));

    private final String DEFAULT_FILE_PATH = "data/dre.txt";

    /**
     * Constructs a new instance of Dre, initializing storage, tasks, and user interface components.
     */
    public Dre() {
        ui = new Ui();
        storage = new Storage(DEFAULT_FILE_PATH);
        assert storage != null : "Failed to initialize storage";
        try {
            tasks = new TaskList(storage.load());
            assert tasks != null : "Failed to initialize tasks";
        } catch (Exception e) {
            ui.generateLoadingErrorString();
            tasks = new TaskList();
            e.printStackTrace();
        }
    }

    /**
     * Runs the main loop of the application, repeatedly reading user commands,
     * parsing them, and executing the corresponding actions until the user exits.
     *
     * @param stage the primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
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

        stage.setTitle("Dre");
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
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add.
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Processes user input, creates two dialog boxes (one for user input and one for Dre's response),
     * and appends them to the dialog container. Clears the user input field after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dreText = getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDreDialog(dreText, dre)
        );
        userInput.clear();
    }

    /**
     * Generates a response based on the user input.
     *
     * @param input the user's input.
     * @return a string response from Dre.
     */
    @FXML
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks, ui, storage);
            return response;
        } catch (DreException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}