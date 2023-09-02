package duke;

import duke.commands.Command;
import duke.commands.CommandException;
import duke.commands.CommandResult;
import duke.storage.Storage;
import duke.storage.StorageException;
import duke.ui.TextUi;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Duke is a task management tool.
 */
public class Duke extends Application {
    private static final String NAME = "Jimmy";
    private static final String TASKS_CACHE_PATH = ".duke-cache";

    // GUI
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public static void main(String[] args) {
        TextUi ui = new TextUi();
        Storage storage = new Storage(TASKS_CACHE_PATH);
        TaskList tasks;

        try {
            tasks = storage.load();
            ui.say(String.format("Loaded existing tasks from %s", TASKS_CACHE_PATH));
        } catch (StorageException e) {
            ui.say(String.format("%s. Initializing empty task list...", e.getMessage()));
            tasks = new TaskList();
        }

        ui.greet(NAME);

        boolean shouldTerminate = false;

        while (!shouldTerminate) {
            String input = ui.getInput();

            try {
                Command command = Command.parse(input);

                if (command == null) {
                    continue;
                }
                if (command.shouldExit()) {
                    shouldTerminate = true;
                }

                CommandResult result = command.run(tasks);

                if (result.isTaskListDirty()) {
                    storage.save(tasks);
                }

                ui.say(result.getResponse().toArray(new String[0]));
            } catch (CommandException e) {
                ui.say(e.getMessage());
            }
        }

        storage.save(tasks);
    }

    @Override
    public void start(Stage stage) {
        // Step 1. Setting up required components
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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}
