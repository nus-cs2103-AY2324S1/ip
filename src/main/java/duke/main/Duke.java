package duke.main;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import duke.commands.Command;
import duke.commands.Parser;
import duke.exception.DeadlineException;
import duke.exception.DeleteException;
import duke.exception.DukeException;
import duke.exception.EventException;
import duke.exception.TodoException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;


/**
 * The main class that initiates the chatbot application.
 */
public class Duke extends Application {

    private final TaskList taskList;
    private final String filePath = "./src/main/resources/duke.txt";
    private final Storage storage = new Storage(filePath);

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/FroggyFrog.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/HolyGod.jpeg"));

    /**
     * Initializes the Chatbot with an empty task list.
     */
    public Duke() {
        this.taskList = new TaskList();
    }

    @Override
    public void start(Stage stage) throws Exception {
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

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(false);

        mainLayout.setPrefSize(400.0, 600.0);

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

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        stage.setScene(scene);
        stage.show();
    }

    public static class DialogBox extends HBox {

        private Label text;
        private ImageView displayPicture;

        public DialogBox(Label l, ImageView iv) {
            text = l;
            displayPicture = iv;

            text.setWrapText(true);
            displayPicture.setFitWidth(100.0);
            displayPicture.setFitHeight(100.0);

            this.setAlignment(Pos.TOP_RIGHT);
            this.getChildren().addAll(text, displayPicture);
        }

        public static DialogBox getUserDialog(Label l, ImageView iv) {
            return new DialogBox(l, iv);
        }

        public static DialogBox getDukeDialog(Label l, ImageView iv) {
            var db = new DialogBox(l, iv);
            db.flip();
            return db;
        }

        /**
         * Flips the dialog box such that the ImageView is on the left and text on the right.
         */
        private void flip() {
            this.setAlignment(Pos.TOP_LEFT);
            ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
            FXCollections.reverse(tmp);
            this.getChildren().setAll(tmp);
        }
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Pu Sa Niang Niang heard: " + input + " ";
    }




    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

    /**
     * Marks a task as done and provides user feedback
     *
     * @param taskIndex Index of the task to be marked as done, starts from '1'
     */
    public void markTaskByBot(int taskIndex) throws DeleteException {
        if (!taskList.isValidListIndex(taskIndex - 1)) {
            throw new DeleteException("Invalid Index of task!");
        }
        taskList.markTaskAsDone(taskIndex - 1);
        saveTasksToFile(this.taskList);
        Ui.showMessage(taskList.getTaskDetails(taskIndex - 1));
    }

    /**
     * Marks a task as not done and provides user feedback.
     *
     * @param taskIndex Index of the task to be marked as not done, starts from '1'
     */
    public void unmarkTaskByBot(int taskIndex) throws DeleteException {
        if (!taskList.isValidListIndex(taskIndex - 1)) {
            throw new DeleteException("detail: Invalid Index of task!");
        }

        taskList.markTaskAsNotDone(taskIndex - 1);
        saveTasksToFile(this.taskList);
        Ui.showMessage(" OK, I've marked this task as not done yet:\n"
                + taskList.getTaskDetails(taskIndex - 1));
    }

    /**
     * Deletes a task from the task list based on the provided input.
     *
     * @param taskIndex The index of the task to be deleted.
     * @throws DeleteException If the input string is not numeric or if the task index is out of valid range.
     */
    public void deleteTaskByBot(int taskIndex) throws DeleteException {
        if (taskIndex < 1 || taskIndex > taskList.getTaskCount()) {
            throw new DeleteException("Invalid Index of task!");
        } else {
            Ui.showMessage(" Noted. I've removed this task:\n"
                    + this.taskList.getTaskDetails(taskIndex - 1)
                    + "\n Now you have " + (taskList.getTaskCount() - 1) + " tasks in the list.\n");
            taskList.deleteTask(taskIndex - 1);
        }
    }

    /**
     * Adds a todo task to the task list and provides user feedback
     *
     * @param description The description of the todo task
     * @throws TodoException If the description is empty
     */
    public void addTodo(String description) throws TodoException {
        Task newTask;
        if (description.isEmpty()) {
            throw new TodoException();
        } else {
            newTask = new Todo(description);
            taskList.addTask(newTask);
            Ui.showMessage(
                    " Got it. I've added this task:\n"
                            + newTask
                            + "\n Now you have " + taskList.getTaskCount() + " tasks in the list.\n");
        }
    }

    /**
     * Adds a deadline task to the task list and provides user feedback
     *
     * @param description  The description of the deadline task
     * @param deadlineDate The deadline date of the deadline task
     * @throws DeadlineException If the description or deadline date is empty
     */
    public void addDeadline(String description, LocalDate deadlineDate) throws DeadlineException {
        if (description.isEmpty() || deadlineDate == null) {
            System.out.println("Error in addDeadline");
            throw new DeadlineException();
        } else {
            Task newTask = new Deadline(description, deadlineDate);
            taskList.addTask(newTask);
            Ui.showMessage(
                    " Got it. I've added this task:\n"
                            + newTask
                            + "\n Now you have " + taskList.getTaskCount() + " tasks in the list.\n");
        }
    }

    /**
     * Adds an event task to the task list and provides user feedback
     *
     * @param description   The description of the event task
     * @param eventFromDate The start date of the event task
     * @param eventToDate   The end date of the event task
     * @throws EventException If the description, start date or end date is empty
     */
    public void addEvent(String description, LocalDate eventFromDate, LocalDate eventToDate) throws EventException {
        if (description.isEmpty() || eventFromDate == null || eventToDate == null) {
            throw new EventException();
        } else {
            Task newTask = new Event(description, eventFromDate, eventToDate);
            taskList.addTask(newTask);
            Ui.showMessage(
                    " Got it. I've added this task:\n"
                            + newTask
                            + "\n Now you have " + taskList.getTaskCount() + " tasks in the list.\n");
        }
    }

    /**
     * Loads the tasks from the storage file
     */
    private void loadTasksFromFile() {
        for (Task taskData : storage.loadTasks()) {
            this.taskList.addTask(taskData);
        }

        if (!this.taskList.isEmpty()) {
            System.out.println(this.taskList);
        }
    }

    /**
     * Saves the tasks to the storage file
     */
    private void saveTasksToFile(TaskList taskList) {
        this.storage.saveTasks(taskList);
    }

    /**
     * Finds tasks containing the given keyword and displays them.
     *
     * @param keyword The keyword to search for.
     */
    public void findTasksByKeyword(String keyword) {
        ArrayList<Task> matchingTasks = taskList.findTasksByKeyword(keyword);
        if (matchingTasks.isEmpty()) {
            Ui.showMessage("No matching tasks found.");
        } else {
            StringBuilder matchingTasksString = new StringBuilder();
            matchingTasksString.append("Here are the matching tasks in your list:\n");
            int count = 1;
            for (Task task : matchingTasks) {
                matchingTasksString.append(count).append(".").append(task).append("\n");
                count++;
            }
            Ui.showMessage(matchingTasksString.toString());
        }
    }

    /**
     * Handles the command based on the command type
     *
     * @param command The command to be handled
     * @return True if the command is not "bye", false otherwise
     * @throws DukeException If the command type is not recognized
     */
    public boolean handleCommand(Command command) throws DukeException {
        String commandType = command.getCommandType();
        String description = command.getDescription();
        int taskIndex = command.getTaskIndex();
        LocalDate deadlineDate = command.getDeadlineDate();
        LocalDate eventFromDate = command.getEventFromDate();
        LocalDate eventToDate = command.getEventToDate();

        switch (commandType) {
        case "mark":
            this.markTaskByBot(taskIndex);
            break;
        case "unmark":
            this.unmarkTaskByBot(taskIndex);
            break;
        case "bye":
            return false;
        case "list":
            Ui.showMessage(this.taskList.toString());
            break;
        case "find":
            this.findTasksByKeyword(description);
            break;
        case "todo":
            this.addTodo(description);
            break;
        case "deadline":
            this.addDeadline(description, deadlineDate);
            break;
        case "event":
            this.addEvent(description, eventFromDate, eventToDate);
            break;
        case "delete":
            this.deleteTaskByBot(taskIndex);
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }

    /**
     * Starts the chatbot application
     */
    public void start() {
        Ui.showWelcomeMessage();
        String userInput;
        Command parsedCommand;

        this.loadTasksFromFile();

        boolean isContinuing = true;
        while (isContinuing) {
            try {
                userInput = Ui.getUserInput();
                parsedCommand = Parser.parse(userInput);
                isContinuing = handleCommand(parsedCommand);
                this.saveTasksToFile(this.taskList);
            } catch (DukeException e) {
                Ui.showErrorMessage(e.getMessage());
            }
        }

        Ui.showGoodByeMessage();
    }


}
