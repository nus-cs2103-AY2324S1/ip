import java.util.ArrayList;

import echobot.command.AddCommand;
import echobot.command.AddNoteCommand;
import echobot.command.Command;
import echobot.command.DeleteCommand;
import echobot.command.FindCommand;
import echobot.command.ListCommand;
import echobot.command.ListNoteCommand;
import echobot.command.MarkCommand;
import echobot.command.UnmarkCommand;
import echobot.note.Note;
import echobot.storage.Storage;
import echobot.task.Task;
import echobot.ui.Ui;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.util.Duration;

/**
 * The EchoBot class represents the main application class for EchoBot, a chat-bot program.
 */
public class EchoBot extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/alles.jpg"));
    private Image echobot = new Image(this.getClass().getResourceAsStream("/google.jpg"));

    private Ui ui;
    private Storage storage;
    private ArrayList<Task> tasks = new ArrayList<>();
    private ArrayList<Note> notes = new ArrayList<>();

    /**
     * The main entry point for the EchoBot application.
     *
     * @param args The command-line arguments passed to the application (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes and sets up the user interface for the EchoBot application.
     *
     * @param stage The primary stage of the JavaFX application.
     */
    @Override
    public void start(Stage stage) {
        ui = new Ui();
        storage = new Storage("./data/duke.txt", "./data/notes.txt");
        tasks = storage.loadTasks();
        notes = storage.loadNotes();

        Platform.runLater(() -> {
            String welcomeMessage = ui.showWelcomeMessage();
            displayEchoBotMessage(welcomeMessage);
        });

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

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null : "User input should not be null";
        String responseText = "";

        if (input.equalsIgnoreCase("bye")) {
            handleByeCommand();
        } else if (input.equalsIgnoreCase("list tasks")) {
            responseText = handleListTasksCommand();
        } else if (input.toLowerCase().startsWith("todo")) {
            responseText = handleAddTaskCommand(Command.TaskType.TODO, input);
        } else if (input.toLowerCase().startsWith("deadline")) {
            responseText = handleAddTaskCommand(Command.TaskType.DEADLINE, input);
        } else if (input.toLowerCase().startsWith("event")) {
            responseText = handleAddTaskCommand(Command.TaskType.EVENT, input);
        } else if (input.toLowerCase().startsWith("mark")) {
            responseText = handleMarkCommand();
        } else if (input.toLowerCase().startsWith("unmark")) {
            responseText = handleUnmarkCommand();
        } else if (input.toLowerCase().startsWith("delete")) {
            responseText = handleDeleteCommand();
        } else if (input.toLowerCase().startsWith("find")) {
            responseText = handleFindCommand();
        } else if (input.toLowerCase().startsWith("note")) {
            responseText = handleNoteCommand(input);
        } else if (input.toLowerCase().startsWith("list notes")) {
            responseText = handleListNotes();
        } else {
            responseText = "Sorry, I don't understand that command.";
        }

        // Display user input and Duke's response in the default format
        displayUserAndEchoBotMessages(input, responseText);

        userInput.clear();
    }

    /**
     * Handles the "bye" command, allowing the user to exit the application.
     */
    private void handleByeCommand() {
        ui.showByeMessage();
        Duration delay = Duration.seconds(1);
        KeyFrame keyFrame = new KeyFrame(delay, event -> Platform.exit());
        Timeline timeline = new Timeline(keyFrame);
        timeline.play();
    }

    /**
     * Handles the "list tasks" command, listing all tasks.
     *
     * @return A string containing the list of tasks.
     */
    private String handleListTasksCommand() {
        ListCommand listCommand = new ListCommand();
        return listCommand.doCommand(tasks, storage, dialogContainer);
    }

    /**
     * Handles adding tasks based on user input.
     *
     * @param taskType The type of task (TODO, DEADLINE, EVENT).
     * @param input    The user input containing task details.
     * @return A response message indicating the result of the command.
     */
    private String handleAddTaskCommand(Command.TaskType taskType, String input) {
        String taskDescription = Command.extractDesc(input, taskType.toString().toLowerCase());
        AddCommand addCommand = null;

        if (taskType == Command.TaskType.TODO) {
            addCommand = new AddCommand(taskType, taskDescription, null, null);
        } else if (taskType == Command.TaskType.DEADLINE) {
            int indexOfBy = taskDescription.indexOf("/by");
            String deadlineDescription = taskDescription.substring(0, indexOfBy).trim();
            String by = taskDescription.substring(indexOfBy + 3).trim();
            addCommand = new AddCommand(taskType, deadlineDescription, by, null);
        } else if (taskType == Command.TaskType.EVENT) {
            int indexOfFrom = taskDescription.indexOf("/from");
            int indexOfTo = taskDescription.indexOf("/to");
            String eventDescription = taskDescription.substring(0, indexOfFrom).trim();
            String from = taskDescription.substring(indexOfFrom + 5, indexOfTo).trim();
            String to = taskDescription.substring(indexOfTo + 3).trim();
            addCommand = new AddCommand(taskType, eventDescription, from, to);
        }

        if (addCommand != null) {
            addCommand.doCommand(tasks, storage, dialogContainer);
            return addCommand.getResponse();
        } else {
            return "Sorry, I couldn't process the task.";
        }

    }

    /**
     * Handles the "mark" command, marking a task as completed.
     *
     * @return A response message indicating the result of the command.
     */
    private String handleMarkCommand() {
        String input = userInput.getText();
        int taskNum = Command.extractTaskNum(input, "mark");
        MarkCommand markCommand = new MarkCommand(taskNum);
        markCommand.doCommand(tasks, storage, dialogContainer);
        return markCommand.getResponse();
    }

    /**
     * Handles the "unmark" command, marking a task as incomplete.
     *
     * @return A response message indicating the result of the command.
     */
    private String handleUnmarkCommand() {
        String input = userInput.getText();
        int taskNum = Command.extractTaskNum(input, "unmark");
        UnmarkCommand unmarkCommand = new UnmarkCommand(taskNum);
        unmarkCommand.doCommand(tasks, storage, dialogContainer);
        return unmarkCommand.getResponse();
    }

    /**
     * Handles the "delete" command, deleting a task from the task list.
     *
     * @return A response message indicating the result of the command.
     */
    private String handleDeleteCommand() {
        String input = userInput.getText();
        int taskNum = Command.extractTaskNum(input, "delete");
        DeleteCommand deleteCommand = new DeleteCommand(taskNum);
        deleteCommand.doCommand(tasks, storage, dialogContainer);
        return deleteCommand.getResponse();
    }

    /**
     * Handles the "find" command, searching for tasks containing a specified keyword.
     *
     * @return A response message listing the tasks that match the keyword.
     */
    private String handleFindCommand() {
        String input = userInput.getText();
        String keyword = Command.extractDesc(input, "find");
        FindCommand findCommand = new FindCommand(keyword);
        findCommand.doCommand(tasks, storage, dialogContainer);
        return findCommand.getResponse();
    }

    /**
     * Handles the "note" command, adding a new note with a title and content.
     *
     * @param input The user input containing the note details.
     * @return A response message indicating the result of the command.
     */
    private String handleNoteCommand(String input) {
        String description = Command.extractDesc(input, "note");
        String[] noteParts = description.split("::", 2);
        if (noteParts.length < 2) {
            return "Please provide both a title and content for your note.";
        } else {
            String title = noteParts[0].trim();
            String content = noteParts[1].trim();

            AddNoteCommand addNoteCommand = new AddNoteCommand(title, content);
            return addNoteCommand.doCommand(notes, storage, dialogContainer);
        }
    }

    /**
     * Handles the "list notes" command, listing all saved notes.
     *
     * @return A response message listing all saved notes.
     */
    private String handleListNotes() {
        ListNoteCommand listNoteCommand = new ListNoteCommand();
        return listNoteCommand.doCommand(notes, storage, dialogContainer);
    }

    /**
     * Displays both the user's message and EchoBot's response in the chat interface.
     *
     * @param userMessage    The user's message.
     * @param echoBotResponse The response generated by EchoBot.
     */
    private void displayUserAndEchoBotMessages(String userMessage, String echoBotResponse) {
        Label userTextLabel = new Label(userMessage);
        Label echoBotTextLabel = new Label(echoBotResponse);

        ImageView userImageView = new ImageView(user);
        ImageView echoBotImageView = new ImageView(echobot);

        DialogBox userDialogBox = DialogBox.getUserDialog(userTextLabel, userImageView);
        DialogBox echoBotDialogBox = DialogBox.getDukeDialog(echoBotTextLabel, echoBotImageView);

        dialogContainer.getChildren().addAll(userDialogBox, echoBotDialogBox);
    }

    /**
     * Displays EchoBot's welcome message and logo in the chat interface.
     *
     * @param echoBotResponse The welcome message generated by EchoBot.
     */
    private void displayEchoBotMessage(String echoBotResponse) {
        Label echoBotTextLabel = new Label(echoBotResponse);

        ImageView echoBotImageView = new ImageView(echobot);

        DialogBox echoBotDialogBox = DialogBox.getDukeDialog(echoBotTextLabel, echoBotImageView);

        dialogContainer.getChildren().addAll(echoBotDialogBox);
    }
}
