package mainDuke;

import java.util.ArrayList;

import mainDuke.StageStuff.DialogBox;
import mainDuke.exceptions.DukeException;
import mainDuke.exceptions.TaskParseException;
import mainDuke.task.Task;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main program, responsible for running everything and connecting components together.
 */
public class Duke extends Application {
    /**
     * Types of task.
     */
    public enum TaskType {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        EVENT,
        TODO,
        DEADLINE,
        FIND
    }
    protected Stage primaryStage;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Scene scene;
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    @Override
    public void start(Stage stage) {
        primaryStage = stage;
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

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add.
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }


    public String getResponse(String input) {
        try {
            TaskType taskType = Parser.parseType(input);
            switch (taskType) {
            case LIST: {
                return TaskList.getListAsString();
            }

            case MARK: {
                int tasknum = Parser.getMarkIndex(input);
                return TaskList.markTask(tasknum);
            }

            case UNMARK: {
                int tasknum = Parser.getUnmarkIndex(input);
                return TaskList.unmarkTask(tasknum);
            }

            case DEADLINE:
            case TODO:
            case EVENT:
                Task nextTask = Parser.parseTask(input, taskType);
                return TaskList.addTask(nextTask);

            case DELETE: {
                return TaskList.deleteTask(Parser.getDeleteIndex(input));
            }

            case FIND: {
                ArrayList<Task> list = TaskList.find(input);
                if (list.size() == 0) {
                    return ("there are no matching tasks!");
                } else {
                    String header = "Here are the matching tasks in your list:\n";
                    String listInString = Ui.getListAsString(list);
                    return header + listInString;
                }
            }

            case BYE: {
                return ("Bye. Hope to see you again soon!");
            }

            default: {
                throw new DukeException("I can't identify your command!");
            }
            }
        } catch (DukeException e) {
            return e.getMessage();
        } catch (TaskParseException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
    }
}
