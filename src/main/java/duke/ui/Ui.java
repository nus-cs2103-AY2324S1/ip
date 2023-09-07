package duke.ui;


import duke.commands.Command;
import duke.commands.Parser;
import duke.exception.DukeException;
import duke.main.Duke;
import duke.ui.DialogBox;
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

import java.util.Scanner;

/**
 * Represents the user interface of the application.
 */
public class Ui extends Application{
    private static final Scanner scanner = new Scanner(System.in);

    private ScrollPane scrollPane;
    private static VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Duke duke = new Duke();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/AncientHuman.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/HolyGod.jpeg"));

    public Ui() {

    }

    /**
     * Prints the welcome message to the user.
     */
    public static void showWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm duke.main.Duke\n What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the goodbye message to the user.
     */
    public static void showGoodByeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message to the user.
     *
     * @param message The message to be printed.
     */
    public void showMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
        Label dukeMessage = new Label(message);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeMessage, new ImageView(dukeImage)));
    }

    /**
     * Prints an error message to the user.
     *
     * @param error The error message to be printed.
     */
    public void showErrorMessage(String error) {
        showMessage("☹ OOPS!!! " + error);
    }

    /**
     * Retrieves the next line of user input.
     *
     * @return The next line of user input.
     */
    public static String getUserInput() {
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        Application.launch(Ui.class, args);
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
//
//        handleInitialGreeting();
    }

    /**
     * Greets the user at the start of the chat.
     */
    private void handleInitialGreeting() {
        Label dukeGreetingText = new Label("hello HUMAN what do you need");
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeGreetingText, new ImageView(dukeImage)));
    }

    private void handleDukeMessage(String string) {
        Label dukeMessage = new Label(string);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeMessage, new ImageView(dukeImage)));
    }

    private void handleUserMessage(String string) {
        Label dukeMessage = new Label(string);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(dukeMessage, new ImageView(userImage)));
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        //        duke.loadTasksFromFile();

        Label userText = new Label(userInput.getText());
        String output = getResponse(userInput.getText());
        Label dukeText = new Label(output);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeImage))
        );
        userInput.clear();

//        if (output.equals("bye")) {
//            Stage stage = (Stage) userInput.getScene().getWindow();
//            stage.close();
//        }
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        String dukeResponse;
        try {
            Command parsedCommand = Parser.parse(input);
            dukeResponse = duke.handleCommand(parsedCommand);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return dukeResponse;
    }
}
