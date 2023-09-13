package duke;

import java.io.IOException;
import java.util.Scanner;
import java.lang.Thread;

import duke.assets.commands.CommandAbstract;
import duke.assets.storage.Storage;
import duke.assets.ui.DialogBox;
import duke.dukeexceptions.CorruptDataException;
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
 * The main class for the ChadGPT chatbot application.
 */
public class Duke extends Application {
    static final String LOGO = "\n   _____ _    _          _____   _____ _____ _______ \n"
            + "  / ____| |  | |   /\\   |  __ \\ / ____|  __ \\__   __|\n"
            + " | |    | |__| |  /  \\  | |  | | |  __| |__) | | |   \n"
            + " | |    |  __  | / /\\ \\ | |  | | | |_ |  ___/  | |   \n"
            + " | |____| |  | |/ ____ \\| |__| | |__| | |      | |   \n"
            + "  \\_____|_|  |_/_/    \\_\\_____/ \\_____|_|      |_|   \n";
    static final String HORIZONTAL = "----------------------------------------------------------"
            + "-----------------------------";

    private final Storage storage = new Storage();
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream(
            "/images/userAvatar.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream(
            "/images/botAvatar.png"));

    /**
     * Print the logo and necessary formatting into terminal at startup
     */
    private void printAtStartup() {
        System.out.println(HORIZONTAL + LOGO + HORIZONTAL);
    }

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

        //Step 2. Formatting the window to look as expected
        stage.setTitle("ChadGPT");
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

        //Part 3. Add functionality to handle user input.
        readFromData();

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        String chatbotResponse = storage.passUserCommand(userInput.getText());
        Label dukeText = new Label(getResponse(chatbotResponse));
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
        return "ChadGPT: " + input;
    }

    private void dukeMessage(String message) {
        Label dukeText = new Label(getResponse(message));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeText, new ImageView(duke)));
    }

    private void readFromData() {
        Scanner sc = new Scanner(System.in);
        boolean successful = false;
        while (!successful) {
            try {
                successful = storage.readFromFile();
            } catch (IOException e) {
                dukeMessage("Unfortunately there was an unexpected error when reading your data file."
                            + "Program will exit in 5 seconds");
                exitChatbot(5);
            } catch (CorruptDataException e) {
                dukeMessage("Data is corrupt at: \"" + e.getCorruptLine() + "\". Please fix and press "
                        + "enter to proceed, or type the command \"exit\" to quit program");

                sendButton.setOnMouseClicked((event) -> {
                    handleCorruptDataUserInput();
                });

                userInput.setOnAction((event) -> {
                    handleCorruptDataUserInput();
                });
            } catch (Exception e) {
                dukeMessage("Unkown exception. Window will close in 5 seconds.");
                exitChatbot(5);
            }
        }
    }

    private void exitChatbot(long delay) {
        try {
            Thread.sleep(delay * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        } finally {
            System.exit(-1);
        }
    }

    private void handleCorruptDataUserInput() {
        String userInputString = userInput.getText();
        Label userText = new Label(userInputString);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(userText, new ImageView(user)));
        userInput.clear();
        if (userInputString.equals("exit")) {
            dukeMessage("Alright! See you again soon. This window will close in 5 seconds");
            exitChatbot(5);
        }
    }
}
