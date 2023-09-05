package duke;

import duke.task.TaskList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the Duke chatbot.
 */
public class Duke extends Application {
    private final UI ui;
    private TaskList list;
    private final Storage storage;
    private ScrollPane scrollPane;
    private TextField userInput;
    private VBox dialogContainer;
    private Button sendButton;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.ui = new UI();
        this.storage = new Storage();
        try {
            this.list = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            ui.displayException(e, dialogContainer);
            this.list = new TaskList();
        }
    }

    protected void performCommand(String line, TaskList list, Storage storage) {
        try {
            CommandType command = Parser.parseCommand(line);
            switch (command) {
            case LIST:
                ui.displayList(list, dialogContainer);
                break;
            case MARK:
                ui.displayDoneTask(list.markDone(Parser.parseOptions(line)),
                        dialogContainer);
                break;
            case UNMARK:
                ui.displayNotDoneTask(list.unmarkDone(Parser.parseOptions(line)),
                        dialogContainer);
                break;
            case TODO:
                ui.displayAddToList(list.addTodoToList(Parser.parseOptions(line)),
                        list.getSize(), dialogContainer);
                break;
            case DEADLINE:
                ui.displayAddToList(list.addDeadlineToList(Parser.parseOptions(line)),
                        list.getSize(), dialogContainer);
                break;
            case EVENT:
                ui.displayAddToList(list.addEventToList(Parser.parseOptions(line)),
                        list.getSize(), dialogContainer);
                break;
            case DELETE:
                ui.displayRemoveFromList(list.deleteFromList(Parser.parseOptions(line)),
                        list.getSize(), dialogContainer);
                break;
            case DEADLINEBY:
                ui.displayTasks(list.getDeadlineDateTasks(Parser.parseOptions(line)),
                        dialogContainer);
                break;
            case EVENTFROM:
                ui.displayTasks(list.getEventStartDateTasks(Parser.parseOptions(line)),
                        dialogContainer);
                break;
            case EVENTTO:
                ui.displayTasks(list.getEventEndDateTasks(Parser.parseOptions(line)),
                        dialogContainer);
                break;
            case FIND:
                ui.displayTasks(list.findTasks(Parser.parseOptions(line)), dialogContainer);
                break;
            default:
                break;
            }
        } catch (DukeException e) {
            ui.displayException(e, dialogContainer);
        }
    }

    @Override
    public void start(Stage stage) {
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        // Styling the window
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(500.0, 700.0);

        scrollPane.setPrefSize(500, 660);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(420.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        ui.greet(dialogContainer);

        // Add functionality to handle user input
        sendButton.setOnMouseClicked((event) -> {
            String text = userInput.getText();
            ui.displayUserInput(text, dialogContainer);
            performCommand(text, list, storage);
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            String text = userInput.getText();
            ui.displayUserInput(text, dialogContainer);
            performCommand(text, list, storage);
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        try {
            storage.saveTasksToFile(list);
            Platform.exit();
        } catch (DukeException e) {
            ui.displayException(e, dialogContainer);
        }
    }
}
