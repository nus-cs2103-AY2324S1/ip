import duke.Parser;
import duke.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Event;
import duke.tasks.Deadline;
import duke.exception.UnknownCommandException;
import duke.exception.EmptyDescriptionException;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Border;
import javafx.scene.layout.Priority;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents the Duke chat bot.
 * A <code>Duke</code> object corresponds to a chat bot that can process user input and respond accordingly.
 */
public class Duke extends Application {
    private static final String DATA_PATH = "./data";
    private static final String FILE_PATH = DATA_PATH + "/duke.txt";
    private final Storage storage = new Storage(FILE_PATH);
    private final TaskList tasks;
    private Stage primaryStage;

    /**
     * Constructs a <code>Duke</code> object.
     *
     * @throws IOException If there is an error loading the tasks from the file.
     */
    public Duke() throws IOException {
        ArrayList<Task> loadedTasks;
        try {
            loadedTasks = storage.loadTasks();
        } catch (IOException e) {
            loadedTasks = new ArrayList<>();
            throw new IOException("Error loading tasks from storage.", e);
        }

        this.tasks = new TaskList(loadedTasks);
    }

    /**
     * Deletes a task from the list.
     *
     * @param taskIndex The index of the task to be deleted.
     * @throws IOException If there is an error saving the tasks to the file.
     * @return The response to the user.
     */
    public String deleteTask(String taskIndex) throws IOException {
        int index = Integer.parseInt(taskIndex) - 1;
        Task removedTask = tasks.removeTask(index);
        String response = "Noted. I've removed this task:\n  " + removedTask +
                "\nNow you have " + tasks.getSize() + " tasks in the list.\n";
        storage.saveTasks(tasks.getTasks());
        return response;
    }

    /**
     * Adds a todo task to the list.
     *
     * @param task The description of the todo task.
     * @throws IOException If there is an error saving the tasks to the file.
     * @return The response to the user.
     */
    public String addTodo(String task) throws IOException {
        Task newTask = new Todo(task);
        tasks.addTask(newTask);
        String response = "Got it. I've added this task:\n  " + newTask +
                "\nNow you have " + tasks.getSize() + " tasks in the list.\n";
        storage.saveTasks(tasks.getTasks());
        return response;
    }

    /**
     * Adds a deadline task to the list.
     *
     * @param task The description of the deadline task.
     * @throws DateTimeParseException If the date format is invalid.
     * @throws IOException If there is an error saving the tasks to the file.
     * @return The response to the user.
     */
    public String addDeadline(String task) throws DateTimeParseException, IOException {
        String[] parts = task.split(" /by ");
        if (parts.length < 2) {
            return "Please use the format 'deadline <task description> /by yyyy-MM-dd'\n";
        }
        LocalDate.parse(parts[1]);
        Task newTask = new Deadline(parts[0], parts[1]);
        tasks.addTask(newTask);
        String response = "Got it. I've added this task:\n  " + newTask +
                "\nNow you have " + tasks.getSize() + " tasks in the list.\n";
        storage.saveTasks(tasks.getTasks());
        return response;
    }

    /**
     * Adds an event task to the list.
     *
     * @param task The description of the event task.
     * @throws DateTimeParseException If the date format is invalid.
     * @throws IOException If there is an error saving the tasks to the file.
     * @return The response to the user.
     */
    public String addEvent(String task) throws DateTimeParseException, IOException {
        StringBuilder response = new StringBuilder();

        String[] parts = task.split(" /from "); // second part will consist the timings
        if (parts.length < 2) {
            return "Please use the format 'event <event description> /from yyyy-MM-dd "
                    + "/to yyyy-MM-dd'\n";
        }

        String[] times = parts[1].split(" /to ");
        if (times.length < 2) {
            return "Please use the format 'event <event description> /from yyyy-MM-dd "
                    + "/to yyyy-MM-dd'\n";
        }

        // Throws exception if invalid format
        LocalDate.parse(times[0]);
        LocalDate.parse(times[1]);

        Task newTask = new Event(parts[0], times[0], times[1]);
        tasks.addTask(newTask);

        response.append("Got it. I've added this task:\n  ").append(newTask)
                .append("\nNow you have ").append(tasks.getSize()).append(" tasks in the list.\n");

        storage.saveTasks(tasks.getTasks());

        return response.toString();
    }


    /**
     * Lists all the tasks in the list.
     *
     * @return The response to the user.
     */
    public String listTasks() {
        StringBuilder response = new StringBuilder();
        response.append("Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.getSize(); i++) {
            response.append((i + 1)).append(".").append(tasks.getTask(i)).append("\n");
        }
        return response.toString();
    }

    /**
     * Marks a task as done.
     *
     * @param task The index of the task to be marked as done.
     * @throws IOException If there is an error saving the tasks to the file.
     * @return The response to the user.
     */
    public String markTaskDone(String task) throws IOException {
        int index = Integer.parseInt(task) - 1;
        Task taskMarked = tasks.markDone(index);
        String response = "\nNice! I've marked this task as done:\n  " +
                taskMarked + "\n";
        storage.saveTasks(tasks.getTasks());
        return response;
    }

    /**
     * Marks a task as not done.
     *
     * @param task The index of the task to be marked as not done.
     * @throws IOException If there is an error saving the tasks to the file.
     * @return The response to the user.
     */
    public String unmarkTaskDone(String task) throws IOException {
        int index = Integer.parseInt(task) - 1;
        Task taskUnmarked = tasks.unmarkDone(index);
        String response = "\nOK, I've marked this task as not done yet:\n  " +
                taskUnmarked + "\n";
        storage.saveTasks(tasks.getTasks());
        return response;
    }

    /**
     * Finds and lists all the tasks that match the keyword.
     *
     * @param keyword The keyword to search for.
     * @return The response to the user.
     */
    public String findAndListTasks(String keyword) {
        StringBuilder dukeResponse = new StringBuilder();
        ArrayList<Task> matchedTasks = tasks.findTasks(keyword);
        if (matchedTasks.size() == 0) {
            dukeResponse.append("No tasks found with the keyword: ").append(keyword).append("\n");
            return dukeResponse.toString();
        }
        dukeResponse.append("Here are the matching tasks in your list:");
        for (int i = 0; i < matchedTasks.size(); i++) {
            dukeResponse.append(i + 1).append(".").append(matchedTasks.get(i));
        }
        return dukeResponse.toString();
    }

    /**
     * Processes the user input and returns the response.
     *
     * @param input The user input.
     * @return The response to the user.
     */
    public String processInput(String input) {
        StringBuilder response = new StringBuilder();

        try {
            String[] words = Parser.parseCommand(input);
            switch (words[0]) {
                case "bye":
                    return "SHUTDOWN";
                case "delete":
                    response.append(deleteTask(words[1]));
                    break;
                case "help":
                    response.append("\nCommands:\n"
                            + "- To add a todo: 'todo [description]'\n"
                            + "- To add a deadline: 'deadline [description] /by [date in format yyyy-MM-dd]'\n"
                            + "- To add an event: 'event [description] /from [start date in format yyyy-MM-dd] /to "
                            + "[end date in format yyyy-MM-dd]'\n");
                    break;
                case "todo":
                    response.append(addTodo(words[1]));
                    break;
                case "deadline":
                    response.append(addDeadline(words[1]));
                    break;
                case "event":
                    response.append(addEvent(words[1]));
                    break;
                case "list":
                    response.append(listTasks());
                    break;
                case "mark":
                    response.append(markTaskDone(words[1]));
                    break;
                case "unmark":
                    response.append(unmarkTaskDone(words[1]));
                    break;
                case "find":
                    response.append(findAndListTasks(words[1]));
                    break;
                default:
                    response.append("Sorry, I don't understand that command.");
                    break;
            }
        } catch (Exception e) {
            response.append(e.getMessage());
        }

        return response.toString();
    }

    /**
     * Starts the chat bot.
     *
     * @param stage The stage to start the chat bot on.
     */
    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;

        // Step 1. Setting up required components
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        Scene scene = new Scene(mainLayout);

        // Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        // This ensures the ScrollPane is stretched to fit its parent, but leaves space for the input and send button below.
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 40.0); // Assuming the height for userInput and sendButton combined is around 40 pixels

        // This keeps the userInput and sendButton anchored to the bottom of the window.
        AnchorPane.setBottomAnchor(sendButton, 10.0);
        AnchorPane.setRightAnchor(sendButton, 10.0);
        AnchorPane.setLeftAnchor(userInput, 10.0);
        AnchorPane.setBottomAnchor(userInput, 10.0);


        // Step 3. Add functionality to handle user input
        sendButton.setOnMouseClicked((event) -> handleUserInput(userInput, dialogContainer));
        userInput.setOnAction((event) -> handleUserInput(userInput, dialogContainer));

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Handles the user input.
     *
     * @param userInput The user input.
     * @param dialogContainer The dialog container.
     */
    private void handleUserInput(TextField userInput, VBox dialogContainer) {
        Label userText = new Label(userInput.getText());
        String response = processInput(userInput.getText());

        if ("SHUTDOWN".equals(response)) {
            Label byeMessage = new Label("Bye. Hope to see you again soon!");
            dialogContainer.getChildren().add(DialogBox.getDukeDialog(byeMessage));

            // Introduce a delay of 2 seconds (or any duration you prefer)
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> primaryStage.close());
            delay.play();
            return;
        }

        Label dukeText = new Label(response);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(userText), DialogBox.getDukeDialog(dukeText));
        userInput.clear();
    }

    /**
     * Represents a dialog box.
     * A <code>DialogBox</code> object corresponds to a dialog box that contains a label.
     */
    public static class DialogBox extends HBox {

        // Set a consistent minimum width for the text boxes
        private static final double MIN_WIDTH = 250.0;

        private DialogBox(Label l, Pos alignment) {
            l.setWrapText(true);

            // Set the text alignment within the HBox
            l.setAlignment(alignment);

            // Set a consistent minimum width for the text
            l.setMinWidth(MIN_WIDTH);

            // Make the label stretch to the maximum width available
            l.setMaxWidth(Double.MAX_VALUE);

            // Make the HBox (DialogBox) stretch its children to maximum width
            HBox.setHgrow(l, Priority.ALWAYS); // Add this line to stretch the Label

            this.getChildren().addAll(l);

            // Setting padding and a border for the text for better visualization
            l.setPadding(new Insets(10));
            l.setBorder(new Border(new BorderStroke(Color.GRAY,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        }


        /**
         * Returns a dialog box containing the user's text.
         *
         * @param l The user's text.
         * @return A dialog box containing the user's text.
         */
        public static DialogBox getUserDialog(Label l) {
            // Set alignment of the user's text to the right
            return new DialogBox(l, Pos.CENTER_RIGHT);
        }

        /**
         * Returns a dialog box containing Duke's text.
         *
         * @param l Duke's text.
         * @return A dialog box containing Duke's text.
         */
        public static DialogBox getDukeDialog(Label l) {
            // Set alignment of Duke's text to the left
            return new DialogBox(l, Pos.CENTER_LEFT);
        }
    }

    /**
     * Starts the chat bot.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}