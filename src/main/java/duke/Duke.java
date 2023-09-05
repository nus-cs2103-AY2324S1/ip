package duke;

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
 * Represents the Duke chatbot.
 */
public class Duke extends Application {
    private UI ui;
    private TaskList list;
    private Storage storage;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image cortana = new Image(this.getClass().getResourceAsStream("/images/cortana.png"));

    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.ui = new UI();
        this.storage = new Storage();
        try {
            this.list = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            ui.displayException(e);
            this.list = new TaskList();
        }
    }

    private void performCommand(String line) {
        try {
            CommandType command = Parser.parseCommand(line);
            switch (command) {
            case LIST:
                ui.displayList(list);
                break;
            case MARK:
                ui.displayDoneTask(list.markDone(Parser.parseOptions(line)));
                break;
            case UNMARK:
                ui.displayNotDoneTask(list.unmarkDone(Parser.parseOptions(line)));
                break;
            case TODO:
                ui.displayAddToList(list.addTodoToList(Parser.parseOptions(line)), list.getSize());
                break;
            case DEADLINE:
                ui.displayAddToList(list.addDeadlineToList(Parser.parseOptions(line)), list.getSize());
                break;
            case EVENT:
                ui.displayAddToList(list.addEventToList(Parser.parseOptions(line)), list.getSize());
                break;
            case DELETE:
                ui.displayRemoveFromList(list.deleteFromList(Parser.parseOptions(line)), list.getSize());
                break;
            case DEADLINEBY:
                ui.displayTasks(list.getDeadlineDateTasks(Parser.parseOptions(line)));
                break;
            case EVENTFROM:
                ui.displayTasks(list.getEventStartDateTasks(Parser.parseOptions(line)));
                break;
            case EVENTTO:
                ui.displayTasks(list.getEventEndDateTasks(Parser.parseOptions(line)));
                break;
            case FIND:
                ui.displayTasks(list.findTasks(Parser.parseOptions(line)));
                break;
            case BYE:
                storage.saveTasksToFile(list);
                ui.exit();
            default:
                break;
            }
        } catch (DukeException e) {
            ui.displayException(e);
        }
    }

    private void handleUserInput() {
        String input = userInput.getText();
        Label userText = new Label(input);
        Label responseText = new Label(input);
        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(user), true),
                new DialogBox(responseText, new ImageView(cortana), false)
        );
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

        scene = new Scene(mainLayout);

        // Styling the window
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Add functionality to handle user input
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        stage.setScene(scene);
        stage.show();
    }
}
