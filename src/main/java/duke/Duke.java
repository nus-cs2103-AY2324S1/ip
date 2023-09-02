package duke;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidIndexException;
import duke.exception.InvalidInputException;
import duke.gui.DialogBox;
import duke.message.Message;
import duke.parser.UserInputParser;
import duke.task.TaskList;
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
 * Represents the Duke program.
 */
public class Duke extends Application {
    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    // GUI
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private final Image user = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png"))
    );
    private final Image duke = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png"))
    );

    /**
     * Constructor for Duke.
     */
    public Duke() {
        String filePath = "data/duke.txt";
        this.ui = new Ui();
        System.out.println(this.ui.getWelcomeMessage());
        this.storage = new Storage(filePath);
        // load data file
        try {
            this.tasks = new TaskList(storage.loadFile());
        } catch (IOException e) {
            System.out.println(this.ui.showError(e.getMessage()));
            System.out.println(this.ui.getLine());
            this.tasks = new TaskList();
        }
    }

    /**
     * Main method.
     * @param args Arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the program.
     */
    private void run() {
        Scanner sc = new Scanner(System.in);
        while (UserInputParser.getIsActive()) {
            String userInput = sc.nextLine();
            try {
                Message message = UserInputParser.parse(userInput, this.tasks);
                System.out.println(message.send());
                this.storage.writeToFile(this.tasks);
            } catch (InvalidInputException e) {
                System.out.println(this.ui.showError(e.getMessage()));
            } catch (InvalidCommandException e) {
                System.out.println(this.ui.showMenu());
            } catch (InvalidIndexException e) {
                System.out.println(this.ui.showInvalidIndexError());
            } catch (IOException e) {
                System.out.println(this.ui.showSaveDataError());
            } finally {
                System.out.println(this.ui.getLine());
            }
        }
    }

    @Override
    public void start(Stage stage) {
        // Step 1. Setting up required components
        // The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // Step 2. Formatting the window to look as expected
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

        // Step 3. Add functionality to handle user input.

        // event listeners
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Add an initial message when the GUI first opens
        Label initialMessage = new Label(this.ui.getWelcomeMessage());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(initialMessage, new ImageView(duke)));
    }

    /**
     * Handles user input.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
        // Note: will not show ByeMessage if user input is "bye"
        if (!UserInputParser.getIsActive()) {
            System.exit(0);
        }
    }

    /**
     * Returns the response to the user input.
     * @param userInput User input.
     * @return Response to the user input.
     */
    private String getResponse(String userInput) {
        try {
            Message message = UserInputParser.parse(userInput, this.tasks);
            this.storage.writeToFile(this.tasks);
            return message.send();
        } catch (InvalidInputException e) {
            return this.ui.showError(e.getMessage());
        } catch (InvalidCommandException e) {
            return this.ui.showMenu();
        } catch (InvalidIndexException e) {
            return this.ui.showInvalidIndexError();
        } catch (IOException e) {
            return this.ui.showSaveDataError();
        }
    }
}
