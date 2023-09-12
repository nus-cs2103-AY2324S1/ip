package seedu.duke;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import seedu.duke.TaskList;
import seedu.duke.Storage;
import seedu.duke.Todo;
import seedu.duke.Deadline;
import seedu.duke.Event;
import seedu.duke.DialogBox;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/patrick.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/spongebob.png"));
    private TaskList tasks;

    static class EmptyDescriptionException extends Exception {
        public EmptyDescriptionException(String task) {
            super("OOPS!!! The description of a " + task + " cannot be empty.");
        }
    }

    static class WrongFormatException extends Exception {
        public WrongFormatException(String message) {
            super(message);
        }
    }

    static class UnknownCommandException extends Exception {
        public UnknownCommandException() {
            super("OOPS I'm sorry, but I don't know what that means :-P");
        }
    }

    @Override
    public void start(Stage stage) {
        String savedString = "";
        Storage storage = new Storage("src/main/java/seedu/duke/data/duke.txt");
        savedString = storage.load();
        tasks = new TaskList(savedString);
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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // more code to be added here later
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

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
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        return processUserInput(input, tasks);
    }

    public static String processUserInput(String userInput, TaskList tasks) {
        String[] inputParts = userInput.split(" ");
        String commandStr = inputParts[0];
        CommandType commandType = getCommandType(commandStr);

        switch (commandType) {
            case BYE:
                break;
            case LIST:
                return listTasks(tasks);
            case MARK:
            case UNMARK:
                int taskIndex = Integer.parseInt(inputParts[1]) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    Task task = tasks.get(taskIndex);
                    if (commandType == CommandType.MARK) {
                        task.markAsDone();
                        return ("Nice! I've marked this task as done: " + task);
                    } else {
                        task.markAsNotDone();
                        return ("OK, I've marked this task as not done yet: " + task);
                    }
                } else {
                    return ("Invalid task index.");
                }
            case DELETE:
                int index = Integer.parseInt(inputParts[1]) - 1;
                Task task = tasks.get(index);
                tasks.remove(index);
                return ("Noted. I've removed this task: \n" + task + "\nNow you have " + tasks.size() + " tasks in the list.");
            case TODO:
            case DEADLINE:
            case EVENT:
                try {
                    return handleTaskCreation(userInput, tasks, commandType);
                } catch (WrongFormatException|EmptyDescriptionException|UnknownCommandException e) {
                    return e.getMessage();
                }
            case FIND:
                return handleFind(tasks, userInput);
            default:
                return "Sorry I dont know what you mean";
        }
        return "Sorry I dont know what you mean";
    }

    public static CommandType getCommandType(String commandStr) {
        try {
            return CommandType.valueOf(commandStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.UNKNOWN;
        }
    }

    public static String listTasks(TaskList tasks) {
        String output = "";
        output += "Here are the tasks in your list:" + "\n";
        for (int i = 0; i < tasks.size(); i++) {
            output += ((i + 1) + ". " + tasks.get(i)) + "\n";
        }
        return output;
    }

    public static String handleFind(TaskList tasks, String filterWord) {
        String output = "";
        output += "Here are the matching in your list:";
        TaskList filteredTaskList = tasks.findmatching(filterWord.substring(5));
        for (int i = 0; i < filteredTaskList.size(); i++) {
            output += ((i + 1) + ". " + filteredTaskList.get(i).toString()) + "\n";
        }
        return output;
    }


    public static String handleTaskCreation(String userInput, TaskList tasks, CommandType commandType) throws EmptyDescriptionException, UnknownCommandException, WrongFormatException {
        String[] inputParts = userInput.split(" ");
        String command = inputParts[0];

        if (commandType == CommandType.TODO) {
            if (inputParts.length <= 1) {
                throw new EmptyDescriptionException(command);
            }
            tasks.add(new Todo(userInput.substring(5)));
        } else if (commandType == CommandType.DEADLINE) {
            if (inputParts.length <= 1) {
                throw new EmptyDescriptionException(command);
            }
            try {
                String[] deadlineParts = userInput.split(" /by ");
                String description = deadlineParts[0].substring(9);
                String by = deadlineParts[1];
                tasks.add(new Deadline(description, by));
            } catch (Exception e) {
                throw new WrongFormatException("OOPS deadlines need to be in this format, deadline return book /by YYYY-MM-DD");
            }
        } else if (commandType == CommandType.EVENT) {
            if (inputParts.length <= 1) {
                throw new EmptyDescriptionException(command);
            }
            try {
                String[] eventParts = userInput.split(" /from | /to ");
                String description = eventParts[0].substring(6);
                String from = eventParts[1];
                String to = eventParts[2];
                tasks.add(new Event(description, from, to));
            } catch (Exception e) {
                throw new WrongFormatException("OOPS events need to be in this format, event project meeting /from Mon 2pm /to 4pm");
            }
        } else {
            throw new UnknownCommandException();
        }
        String output = "";
        output += "Got it. I've added this task: \n";
        output += "  " + tasks.get(tasks.size() - 1) + "\n";
        output += "Now you have " + tasks.size() + " tasks in the list. \n";
        return output;
    }

    enum CommandType {
        LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND, BYE, UNKNOWN
    }

}