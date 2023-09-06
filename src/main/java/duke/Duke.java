package duke;

import duke.command.Command;
import duke.command.InvalidCommand;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Initialises Duke and starts the interaction with the user.
 */
public class Duke extends Application {

    private Storage storage;

    private TaskList list;

    private Ui ui;

    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Initialises the application with the necessary components.
     */
    public Duke() {
        ui = new Ui();

        storage = new Storage(ui);
        parser = new Parser(ui);
        try {
            list = new TaskList(storage.readFile());
        } catch (Exception e) {
            ui.showError("Error reading from file.");
            list = new TaskList();
        }
    }

    /**
     * Runs the application.
     * Starts the interaction with the user.
     */
    public void run() {
        ui.showStartMessage();

        Command command = new InvalidCommand();
        while (command.type != Command.CommandType.BYE) {
            command = parser.parseCommand(ui.readCommand());
            command.execute(list, ui);
        }

        // Write to file
        try {
            storage.writeFile(list);
        } catch (Exception e) {
            ui.showError("Error writing to file.");
        }
    }

    /**
     * Starts the application by calling the run method.
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
    }
}
