package duke;

import GUI.DialogBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.image.Image;

/**
 * The Duke class represents a command-line chatbot that allows users to manage their tasks. Users can interact
 * with Duke by providing various commands to add, list, mark, unmark, delete, and save tasks. Duke also stores
 * tasks in a file for persistence across sessions.
 *
 * Duke supports the following commands:
 * - "bye": Exits the chatbot and saves the task list to a file.
 * - "list": Lists all tasks in the current task list.
 * - "mark <task_id>": Marks a task as done by its ID.
 * - "unmark <task_id>": Unmarks a previously marked task.
 * - "todo <description>": Adds a to-do task with a description.
 * - "event <description> /from <datetime> /to <datetime>": Adds an event task with a description, start date, and end date.
 * - "deadline <description> /by <datetime>": Adds a deadline task with a description and due date.
 * - "delete <task_id>": Deletes a task by its ID.
 *
 * Duke also provides a graphical user interface (GUI) using JavaFX for a more interactive user experience.
 * Users can input commands in a text field, and Duke's responses are displayed in a scrollable chat interface.
 * The GUI version of Duke includes a send button for user input and displays user and Duke avatars for each message.
 *
 * Duke's chat interface includes support for features like listing tasks, marking tasks as done, adding tasks,
 * deleting tasks, and searching for tasks. Users can also exit the chatbot with the "bye" command.
 *
 * Duke stores tasks in a file to ensure persistence across sessions, and it can load tasks from this file when
 * initialized.
 *
 * @author LuoZYi
 */
public class Duke extends Application {
    private final String filePath = "./data/duke.txt";
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    // JavaFX
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private String chatBotResponse;

    /**
     * Constructs a new Duke instance.
     * Initializes the user interface (UI), storage, task list, and parser components. It also attempts to load
     * tasks from a file. If loading fails, it creates an empty task list.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();

        // Ensure the file path is not empty
        assert filePath != null && !filePath.isEmpty() : "File path must not be empty";

        // Ensure the storage, UI, and taskList are properly initialized
        assert storage != null : "Storage must be initialized";
        assert ui != null : "UI must be initialized";
        assert taskList != null : "TaskList must be initialized";
        try {
            taskList = new TaskList(storage.load());
        } catch (CustomException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Runs the Duke chatbot.
     */
    public void run() {
        try {
            while (true) {
                String response = ui.getUserInput();

                // Use the parseCommandType method to determine the command type
                Parser.CommandType commandType = parser.parseCommandType(response);

                switch (commandType) {
                    case BYE:
                        ui.closeScanner();
                        try {
                            storage.save(taskList.getTaskArrayList());
                        } catch (CustomException e) {
                            System.out.println("Error saving tasks: " + e.getMessage());
                        }
                        return;
                    case LIST:
                        ui.showMessage("Here are the tasks in your list:");
                        taskList.listTasks();
                        continue;
                    case MARK:
                        try {
                            int markId = Integer.parseInt(response.substring(5).trim());
                            String markMessage = taskList.markTaskAsDone(markId);
                            ui.showMessage(markMessage);
                        } catch (NumberFormatException e) {
                            ui.showMessage("Invalid format. Key in an integer within range");
                        }
                        continue;
                    case UNMARK:
                        try {
                            int unmarkId = Integer.parseInt(response.substring(7).trim());
                            String unmarkMessage = taskList.unmarkTask(unmarkId);
                            ui.showMessage(unmarkMessage);
                        } catch (NumberFormatException e) {
                            ui.showMessage("Invalid format. Key in an integer within range");
                        }
                        continue;
                    case FIND:
                        ui.showMessage("Here are the matching tasks in your list:");
                        taskList.findTasks(response.substring(5));
                        continue;
                    case TODO:
                        String todoDescription = response.substring(5);
                        Task toDo = parser.addTodoTask(todoDescription);
                        String todoMessage = taskList.addTask(toDo);
                        ui.showMessage(todoMessage);
                        continue;
                    case EVENT:
                        String[] eventParts = response.split("/from|/to");
                        if (eventParts.length == 3) {
                            String eventDescription = eventParts[0].substring(6).trim();
                            String from = eventParts[1].trim();
                            String to = eventParts[2].trim();
                            Task event = parser.addEventTask(eventDescription, from, to);
                            String eventMessage = taskList.addTask(event);
                            ui.showMessage(eventMessage);
                        } else {
                            ui.showMessage("Invalid event format. Use 'event <description> /from <datetime> /to <datetime>'.");
                        }
                        continue;
                    case DEADLINE:
                        String[] deadlineParts = response.split("/by", 2);
                        if (deadlineParts.length == 2) {
                            String deadlineDescription = deadlineParts[0].substring(9).trim();
                            String dateTimeString = deadlineParts[1].trim();
                            Task deadline = parser.addDeadlineTask(deadlineDescription, dateTimeString);
                            String deadlineMessage = taskList.addTask(deadline);
                            ui.showMessage(deadlineMessage);
                        } else {
                            String message = "Invalid deadline format. Use 'deadline <description> /by <datetime>'.";
                            ui.showMessage(message);
                        }
                        continue;
                    case DELETE:
                        int deleteId = Integer.parseInt(response.substring(7));
                        String deleteMessage = taskList.deleteTask(deleteId);
                        ui.showMessage(deleteMessage);
                        continue;
                    case HELP:
                        ui.showMessage(showHelp());
                    case INVALID:
                        ui.showMessage("I'm sorry, I don't understand that command.");
                }
                try {
                    storage.save(taskList.getTaskArrayList());
                } catch (CustomException e) {
                    System.out.println("Error saving tasks: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            try {
                storage.save(taskList.getTaskArrayList());
            } catch (CustomException customException) {
                System.out.println("Error saving tasks: " + customException.getMessage());
            }
        }
    }

    /**
     * Initializes the JavaFX graphical user interface (GUI) for Duke.
     * Sets up the chat interface, input field, and send button. It also configures the appearance of
     * the GUI window and sets event handlers for user interactions.
     *
     * @param stage The JavaFX stage where the GUI is displayed.
     */
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

        stage.setScene(scene);
        stage.show();


        //Formatting the window to look as expected
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
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }




    /**
     * Handles user input in the GUI.
     * This method processes user input, updates the chat interface with user and Duke's responses,
     * and clears the input field for the next user message.
     */

    private void handleUserInput() {
        String userInputText = userInput.getText();

        // Ensure userInputText is not null
        assert userInputText != null : "User input text must not be null";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInputText, user),
                DialogBox.getDukeDialog(getResponse(userInputText), duke)
        );

        userInput.clear();
    }

    /**
     * Retrieves Duke's response for a given user input.
     * This method determines the type of user command, processes it, and generates a response.
     * It handles various commands such as listing tasks, marking tasks as done, adding tasks, and more.
     *
     * @param input The user's input command.
     * @return The response generated by Duke.
     */
    public String getResponse(String input) {
        Parser.CommandType commandType = parser.parseCommandType(input);

        switch (commandType) {
            case BYE:
                try {
                    storage.save(taskList.getTaskArrayList());
                    return ui.exit();
                } catch (CustomException e) {
                    return "Error saving tasks: " + e.getMessage();
                }

            case LIST:
                return taskList.listTasks();
            case MARK:
                try {
                    int markId = Integer.parseInt(input.substring(5).trim());
                    String markMessage = taskList.markTaskAsDone(markId);
                    return markMessage;
                } catch (NumberFormatException e) {
                    return "Invalid format. Key in an integer within range";
                }
            case UNMARK:
                try {
                    int unmarkId = Integer.parseInt(input.substring(7).trim());
                    String unmarkMessage = taskList.unmarkTask(unmarkId);
                    return unmarkMessage;
                } catch (NumberFormatException e) {
                    return "Invalid format. Key in an integer within range";
                }
            case FIND:
                return taskList.findTasks(input.substring(5));
            case TODO:
                String todoDescription = input.substring(5);
                Task toDo = parser.addTodoTask(todoDescription);
                String todoMessage = taskList.addTask(toDo);
                return todoMessage;
            case EVENT:
                String[] eventParts = input.split("/from|/to");
                if (eventParts.length == 3) {
                    String eventDescription = eventParts[0].substring(6).trim();
                    String from = eventParts[1].trim();
                    String to = eventParts[2].trim();
                    Task event = parser.addEventTask(eventDescription, from, to);
                    String eventMessage = taskList.addTask(event);
                    return eventMessage;
                } else {
                    return "Invalid event format. Use 'event <description> /from <datetime> /to <datetime>'.";
                }
            case DEADLINE:
                String[] deadlineParts = input.split("/by", 2);
                if (deadlineParts.length == 2) {
                    String deadlineDescription = deadlineParts[0].substring(9).trim();
                    String dateTimeString = deadlineParts[1].trim();
                    Task deadline = parser.addDeadlineTask(deadlineDescription, dateTimeString);
                    String deadlineMessage = taskList.addTask(deadline);
                    return deadlineMessage;
                } else {
                    return "Invalid deadline format. Use 'deadline <description> /by <datetime>'.";
                }
            case DELETE:
                try {
                    int deleteId = Integer.parseInt(input.substring(7));
                    String deleteMessage = taskList.deleteTask(deleteId);
                    return deleteMessage;
                } catch (NumberFormatException e) {
                    return "Invalid format. Key in an integer within range";
                }
            case INVALID:
                return "I'm sorry, I don't understand that command.";
            case HELP:
                return showHelp();
            default:
                return "Unknown command.";
        }
    }

    /**
     * Exits the Duke chatbot and saves tasks before shutting down.
     * This method is called when the user enters the "bye" command. It saves the task list to a file
     * and exits the program gracefully.
     */
    public void exit() {
        try {
            // Ensure storage and taskList are initialized
            assert storage != null : "Storage must be initialized";
            assert taskList != null : "TaskList must be initialized";
            storage.save(taskList.getTaskArrayList());
            chatBotResponse = ui.exit();
        } catch (CustomException e) {
            chatBotResponse = "Error saving tasks: " + e.getMessage();
        }
        System.exit(0);
    }

    /**
     * Displays a help message explaining the available commands and their usage.
     */
    public String showHelp() {
        StringBuilder helpMessage = new StringBuilder();
        helpMessage.append("Welcome to Duke! Here are the available commands:\n");
        helpMessage.append("- 'bye': Exits the chatbot and saves the task list to a file.\n");
        helpMessage.append("- 'list': Lists all tasks in the current task list.\n");
        helpMessage.append("- 'mark <task_id>': Marks a task as done by its ID.\n");
        helpMessage.append("- 'unmark <task_id>': Unmarks a previously marked task.\n");
        helpMessage.append("- 'todo <description>': Adds a to-do task with a description.\n");
        helpMessage.append("- 'event <description> /from <datetime> /to <datetime>': Adds an event task with a description, start date, and end date.\n");
        helpMessage.append("- 'deadline <description> /by <datetime>': Adds a deadline task with a description and due date.\n");
        helpMessage.append("- 'delete <task_id>': Deletes a task by its ID.\n");
        helpMessage.append("- 'find <keyword>': Searches for tasks containing the specified keyword.\n");
        helpMessage.append("To use a command, simply type it in the chat and press 'Send'.\n");
        helpMessage.append("For example: 'todo Buy groceries' or 'mark 1'.\n");

        return helpMessage.toString();
    }


    public static void main(String[] args) {
        Duke chatBot = new Duke();
        chatBot.run();
    }
}
