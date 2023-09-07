package duke.ui;

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
    private final Duke duke = new Duke();


    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/AncientHuman.jpeg")));
    private final Image dukeImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/HolyGod.jpeg")));


    /**
     * Prints a message to the user.
     *
     * @param message The message to be printed.
     */
    public void showMessage(String message) {
        Label dukeMessage = new Label("____________________________________________________________\n"
                + message + "\n"
                + "____________________________________________________________");
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeMessage, new ImageView(dukeImage)));
    }

    @Override
    public void start(Stage stage) throws Exception {
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        stage.setScene(scene);
        stage.show();

        handleFileLoading();
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
