package geraldbot;

import java.util.ArrayList;

import geraldbot.exception.DukeException;
import geraldbot.task.Task;
import geraldbot.util.Parser;
import geraldbot.util.Storage;
import geraldbot.util.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * The main class that represents the Duke chatbot application.
 * Duke is a task manager that can handle various commands to manage tasks.
 */
public class Duke extends Application {
    private final Parser parser;
    private final Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructs a Duke object and initializes the user interface and parser.
     * Reads task data from storage and initializes the task list.
     */
    public Duke() {
        Storage storage = new Storage("./data/data.txt");
        ArrayList<Task> taskList = storage.read();
        this.ui = new Ui();
        this.parser = new Parser(storage, taskList);
    }

    /**
     * Main method to start the Duke chatbot application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.run();
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

        // more code to be added here later
    }

    /**
     * Runs the main loop of the Duke application.
     * The loop repeatedly reads user input, processes it using the parser,
     * and displays the corresponding output or error messages.
     */
    public void run() {
        ui.showLine();
        ui.greet();
        ui.showLine();

        while (true) {
            try {
                String input = ui.readInput();
                if (input.equals("bye")) {
                    ui.showLine();
                    ui.bye();
                    ui.showLine();
                    break;
                } else {
                    ui.showLine();
                    parser.parse(input);
                    ui.showLine();
                }
            } catch (DukeException e) {
                System.out.println(e);
                ui.showLine();
            }
        }
    }
}
