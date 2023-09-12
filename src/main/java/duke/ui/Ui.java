package duke.ui;

import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

import duke.commands.Command;
import duke.commands.Parser;
import duke.exception.DukeException;
import duke.main.Duke;
import javafx.animation.PauseTransition;
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
import javafx.util.Duration;

/**
 * Represents the user interface of the application.
 */
public class Ui extends Application {
    private static final Scanner scanner = new Scanner(System.in);
    private static VBox dialogContainer;
    private ScrollPane scrollPane;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private AnchorPane mainLayout = new AnchorPane();
    private final Duke duke = new Duke();


    private final Image userImage = new Image(loadResource("/AncientHuman.jpeg"));
    private final Image dukeImage = new Image(loadResource("/HolyGod.jpeg"));;

    private static final double MAIN_WIDTH = 400.0;
    private static final double MAIN_HEIGHT = 600.0;
    private static final double SCROLLPANE_WIDTH = 385.0;
    private static final double SCROLLPANE_HEIGHT = 535.0;
    private static final double USER_INPUT_WIDTH = 325.0;
    private static final double SEND_BUTTON_WIDTH = 55.0;
    private static final double ANCHOR_CONST = 1.0;

    @Override
    public void start(Stage stage) throws Exception {
        initializeGuiComponents();
        setEventHandlers();
        formatWindow(stage);
        handleFileLoading();
    }

    private void initializeGuiComponents() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);
    }

    private void setEventHandlers() {
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void formatWindow(Stage stage) {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(MAIN_HEIGHT);
        stage.setMinWidth(MAIN_WIDTH);

        mainLayout.setPrefSize(MAIN_WIDTH, MAIN_HEIGHT);

        // Format ScrollPane
        scrollPane.setPrefSize(SCROLLPANE_WIDTH, SCROLLPANE_HEIGHT);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        // Format userInput and sendButton
        userInput.setPrefWidth(USER_INPUT_WIDTH);
        sendButton.setPrefWidth(SEND_BUTTON_WIDTH);

        // Set Anchors
        AnchorPane.setTopAnchor(scrollPane, ANCHOR_CONST);
        AnchorPane.setBottomAnchor(sendButton, ANCHOR_CONST);
        AnchorPane.setRightAnchor(sendButton, ANCHOR_CONST);
        AnchorPane.setLeftAnchor(userInput, ANCHOR_CONST);
        AnchorPane.setBottomAnchor(userInput, ANCHOR_CONST);

        stage.setScene(scene);
        stage.show();
    }

    private InputStream loadResource(String path) {
        InputStream stream = this.getClass().getResourceAsStream(path);
        if (stream == null) {
            throw new IllegalArgumentException("Resource not found: " + path);
        }
        return stream;
    }


    /**
     * Prints the initial greeting message to the user.
     */
    private void handleFileLoading() throws DukeException {
        Label dukeLoadTasks = new Label("hello HUMAN what do you need \n"
                + duke.loadTasksFromFile());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeLoadTasks, new ImageView(dukeImage)));

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {

        Label userText = new Label(userInput.getText());
        String output = getResponse(userInput.getText());
        Label dukeText = new Label(output);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeImage))
        );
        userInput.clear();

        if (output.equals("Bye. Hope to see you again soon!")) {
            userInput.setDisable(true);
            sendButton.setDisable(true);

            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> {
                Stage stage = (Stage) userInput.getScene().getWindow();
                stage.close();
            });
            delay.play();
        }
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     *
     * @param input The user input.
     * @return The response to the user input.
     */
    private String getResponse(String input) {
        String dukeResponse;
        try {
            Command parsedCommand = Parser.parse(input);
            dukeResponse = duke.handleCommand(parsedCommand);
            duke.saveTasksToFile();
        } catch (DukeException e) {
            return e.getMessage();
        }
        return dukeResponse;
    }

    public static void main(String[] args) {
        Application.launch(Ui.class, args);
    }
}
