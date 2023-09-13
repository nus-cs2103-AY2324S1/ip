package duke;

import javafx.application.Application;

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

public class Duke extends Application {
    private static final int MAX_TASKS = 100;
    private static final String DATA_FILE_PATH = "./docs/duke.txt";

    private static TaskList tasks = new TaskList();
    private static Ui ui = new Ui();
    private static Storage storage = new Storage(DATA_FILE_PATH);
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image biubiu = new Image(this.getClass().getResourceAsStream("/images/BiuBiu.jpg"));

    /**
     * The main entry point of the Duke program.
     *
     * @param args  Command-line arguments (not used).
     */
    public static void main (String[] args) {
        ui.showWelcome();

        try {
            tasks = ui.loadTasks("./docs/duke.txt");
        } catch (Exception e) {
            ui.showLoadingError();
        }

        boolean isExit = false;

        while (!isExit) {
            String userCommand = ui.readCommand();
            isExit = ui.handleCommand(userCommand, tasks, storage);
        }
    }

    @Override
    public void start (Stage stage) {
//        Label helloWorld = new Label("Hello World!");
//        Scene scene = new Scene(helloWorld);
        // Step 1
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

        // Step 2
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

        // Step 3
        sendButton.setOnMouseClicked(event -> {
            handleUserInput();
        });

        userInput.setOnAction((event -> {
            handleUserInput();
        }));

        // Scroll down to the end automatically
        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));
    }

    private Label getDialogLabel (String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, biubiu)
        );
        userInput.clear();
    }
    int taskCount = 0;
    public String getResponse (String input) {
        try {
            String output = "";

            assert !input.isEmpty();

            if (input.equalsIgnoreCase("bye")) {
                storage.saveTasks(tasks);
                return "Bye. Have a great day!";
            } else if (input.equalsIgnoreCase("list")) {
                output = "Here are the tasks in your list:";
                for (int i = 0; i < tasks.size(); i++) {
                    output += "\n " + (i + 1) + ". " + tasks.getTask(i);
                }
                return output;
            }

            String[] parts = input.split(" ", 2);
            String commandType = parts[0].toLowerCase();
            String command = parts[1];

            switch (commandType) {
            case "mark":
                int doneTaskIndex = Integer.parseInt(command) - 1;
                tasks.markTaskAsDone(doneTaskIndex);
                output = "Nice! I've marked this task as done:";
                output += "\n " + tasks.getTask(doneTaskIndex);
                storage.saveTasks(tasks);
                return output;
            case "unmark":
                int notDoneTaskIndex = Integer.parseInt(command) - 1;
                tasks.markTaskAsNotDone(notDoneTaskIndex);
                output = "Nice! I've marked this task as not done yet:";
                output += "\n " + tasks.getTask(notDoneTaskIndex);
                storage.saveTasks(tasks);
                return output;
            case "todo":
                if (parts.length == 1) {
                    return "What you want to do?";
                }
            case "deadline":
                if (parts.length == 1) {
                    return "What deadline do you have?";
                }
            case "event":
                if (parts.length == 1) {
                    return "What event do you have?";
                }
                Task newTask = Parser.parse(input);
                if (newTask == null) {
                    break;
                }

                tasks.addTask(newTask);
                taskCount++;
                output = "Got it. I've added this task:";
                output += "\n " + tasks.getTask(taskCount - 1);
                System.out.println("\nNow you have " + taskCount + " tasks in the list.");
                storage.saveTasks(tasks);
                return output;
            case "delete":
                int deletedTaskIndex = Integer.parseInt(parts[1]) - 1;
                tasks.deleteTask(deletedTaskIndex);
                taskCount--;
                output = "OK, I've removed this task.";
                output += "\nNow you have " + taskCount + " tasks in the list.";
                storage.saveTasks(tasks);
                return output;
            case "find":
                if (parts.length == 1) {
                    return "Please specify a keywork to search for.";
                }
                TaskList matchingTasks = tasks.findTasksByKeyword(command);
                output = "Here are the matching tasks: ";
                for (int i = 0; i < matchingTasks.size(); i++) {
                    output += "\n " + (i + 1) + "." + matchingTasks.getTask(i);
                }
                return output;
            default:
                return "Invalid command format.";
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return input;
        }
        return "";
    }
}


/*
TODO | read book
DEADLINE | project | 2022-10-10
EVENT | meeting | 2023-06-12 1400 | 2023-06-12 1800
 */