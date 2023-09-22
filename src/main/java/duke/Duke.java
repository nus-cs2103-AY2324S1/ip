package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Duke extends Application {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;;

    private MainWindow mainWindow;



    public Duke() {
        String filePath = "tasks.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }



    public void run() {
        ui.showWelcomeMessage();
    }

    private String handleExit() {
        storage.save(tasks, "tasks.txt");
        ui.closeScanner();
        return ui.showExitMessage();
    }

    private String handleList() {
        return ui.showTaskList(tasks);
    }

    private String handleFind(String description) {
        return tasks.findTasksContainingKeyword(description);
    }

    private String handleUnmark(String description) {
        // if user inputs task number, check if it is even an integer, and whether it is within range
        try {
            int taskNumber = Integer.parseInt(description) - 1;
            if (taskNumber >= 0 && taskNumber < tasks.size()) {
                return tasks.unmarkTask(taskNumber);
            } else {
                return ui.showError("Task number out of range.");
            }

        } catch (NumberFormatException e) {
            return ui.showError("Invalid task number. Please provide a valid integer.");
        }
    }

    private String handleMark(String description) {
        // if user inputs task number, check if it is even an integer, and whether it is within range
        try {
            int taskNumber = Integer.parseInt(description) - 1;
            if (taskNumber >= 0 && taskNumber < tasks.size()) {
                return tasks.markTaskAsDone(taskNumber);
            } else {
                return ui.showError("Task number out of range.");
            }
        } catch (NumberFormatException e) {
            return ui.showError("Invalid task number. Please provide a valid integer.");
        }
    }
    private String handleTodo(String description) {
        if (description.isEmpty()) {
                return "OOPS!!! The description of a Todo cannot be empty.";
        } else {

            Todo todo = new Todo(description, false);
            return tasks.addTask(todo);
        }
    }
    private String handleDeadline(String description) {
        if (description.isEmpty()) {
            return "OOPS!!! The description of a deadline cannot be empty.";
        } else {
            // Find the index of the deadline separator "/"
            int separatorIndex = description.indexOf('/');

            if (separatorIndex != -1) { // Ensure the separator exists in the input
                // Extract the task description and deadline

                String descriptionString = description.substring(0, separatorIndex).trim();
                String deadline = description.substring(separatorIndex + 4).trim();
                String pattern = "\\d{4}/\\d{2}/\\d{2}";
                Pattern datePattern = Pattern.compile(pattern);
                Matcher matcher = datePattern.matcher(deadline);
                if (matcher.find()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDate localDateDeadline = LocalDate.parse(deadline, formatter);
                    Deadline deadlineTask = new Deadline(descriptionString, false, localDateDeadline);
                    return tasks.addTask(deadlineTask);

                } else {
                    return "Please input your deadline in YYYY/MM/DD format";
                }
            } else {
                return "Invalid input format for deadline. Please input in the following format: <deadline> <description> /by <YYYY/MM/DD> ";
            }
        }
    }

    private String handleEvent(String description) {
        if (description.isEmpty()) {
            return "OOPS!!! The description of an event cannot be empty.";
        } else {
            // Find the indices of the time separators
            int fromIndex = description.indexOf("/from");
            int toIndex = description.indexOf("/to");

            if (fromIndex != -1 && toIndex != -1) {
                // Extract the task description, startTime, and endTime
                String descriptionString = description.substring(0, fromIndex).trim();
                String startTime = description.substring(fromIndex + 5, toIndex).trim();
                String endTime = description.substring(toIndex + 3).trim();

                // Create a new Event object
                Event eventTask = new Event(descriptionString, false, startTime, endTime);
                return tasks.addTask(eventTask);

            } else {
                return "Invalid input format for event command.";
            }
        }
    }

    private String handleDelete(String description) {
        try {
            int taskNumber = Integer.parseInt(description) - 1;
            if (taskNumber >= 0 && taskNumber < tasks.size()) {
                return tasks.deleteTask(taskNumber);
            } else {
                return ui.showError("Task number out of range.");
            }
        } catch (NumberFormatException e) {
            return ui.showError("Invalid task number. Please provide a valid integer.");
        }
    }

    private String handleInvalid() {
        return ui.showError("Invalid command. Please try again.");
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
        mainWindow = new MainWindow();
        mainWindow.setDuke(this);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);


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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
    }

    private void handleUserInput() {
        // Handle user input and display responses in the GUI.
        String input = userInput.getText();
        String response = getResponse(input);
        ui.addToDialog("You: " + input);
        ui.addToDialog("Duke: " + response);
        userInput.clear();
    }

    String getResponse(String userInput) {
        Command command = Parser.parseCommand(userInput);
        String description = Parser.parseDescription(userInput);
        switch (command) {
            //if user wants to exit, tasks are saved and exit message is shown
            case EXIT:
                return handleExit();
            // lists all the tasks out
            case LIST:
                return handleList();

            case FIND:
                return handleFind(description);
            // unmarks task
            case UNMARK:
                return handleUnmark(description);

            //marks the task
            case MARK:
                return handleMark(description);
            // if user wants to add a todo object
            case TODO:
                return handleTodo(description);
            // if user wants to input deadline
            case DEADLINE:
                return handleDeadline(description);
            // if user wants to input an event
            case EVENT:
                return handleEvent(description);

            // if user wants to delete existing task
            case DELETE:
                return handleDelete(description);

            // if user just enters a completely invalid command
            case INVALID:
                return handleInvalid();

        }

        return "Duke heard: " + userInput;
    }


    public static void main(String[] args) {
        launch(args);
    }

}


