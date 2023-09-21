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
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The EchoBot class represents the main application class for EchoBot, a chatbot program.
 */
public class EchoBot extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/user.png"));
    private Image echobot = new Image(this.getClass().getResourceAsStream("/echobot.png"));

    private Ui ui;
    private Storage storage;
    private ArrayList<Task> tasks = new ArrayList<>();
    private ArrayList<Note> notes = new ArrayList<>();

    private enum CommandType {
        ADD_TODO,
        ADD_EVENT,
        ADD_DEADLINE,
        ADD_NOTE,
        FIND,
        UNMARK,
        MARK,
        DELETE,
        LIST_TASK,
        LIST_NOTE
    }

    /**
     * The main entry point for the EchoBot application.
     *
     * @param args The command-line arguments passed to the application (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        initializeUI(stage);
        setupUserInputHandlers();
        loadWelcomeMessage();
    }

    private void initializeUI(Stage stage) {
        ui = new Ui();
        storage = new Storage("./data/duke.txt", "./data/notes.txt");
        tasks = storage.loadTasks();
        notes = storage.loadNotes();

        VBox mainLayout = createMainLayout();
        scene = new Scene(mainLayout);

        configureStage(stage);
        stage.setScene(scene);
        stage.show();
    }

    private VBox createMainLayout() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        VBox.setVgrow(dialogContainer, Priority.ALWAYS);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane inputLayout = createInputLayout();

        VBox mainLayout = new VBox(scrollPane, inputLayout);

        configureScrollPane();
        configureInputLayout();

        return mainLayout;
    }

    private AnchorPane createInputLayout() {
        AnchorPane inputLayout = new AnchorPane();
        inputLayout.getChildren().addAll(userInput, sendButton);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        AnchorPane.setTopAnchor(dialogContainer, 1.0); // Set the top anchor

        return inputLayout;
    }

    private void configureScrollPane() {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    }

    private void configureInputLayout() {
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
    }

    private void configureStage(Stage stage) {
        stage.setTitle("EchoBot");
        stage.setResizable(false);
        stage.setMinHeight(500.0);
        stage.setMinWidth(400.0);
    }

    private void setupUserInputHandlers() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void loadWelcomeMessage() {
        Platform.runLater(() -> {
            String welcomeMessage = ui.showWelcomeMessage();
            displayEchoBotMessage(welcomeMessage);
        });
    }

    private void handleUserInput() {
        String input = userInput.getText().trim();
        assert input != null : "User input should not be null";
        String responseText = "";

        if (input.equalsIgnoreCase("bye")) {
            handleByeCommand();
            return;
        }

        CommandType commandType = getCommandType(input);
        if (commandType != null) {
            responseText = handleCommand(commandType, input);
        } else {
            responseText = "Sorry, I don't understand that command.";
        }

        // Display user input and Duke's response in the default format
        displayUserAndEchoBotMessages(input, responseText);

        userInput.clear();
    }

    private CommandType getCommandType(String input) {
        String command = input.toLowerCase();
        if (command.startsWith("todo")) {
            return CommandType.ADD_TODO;
        } else if (command.startsWith("event")) {
            return CommandType.ADD_EVENT;
        } else if (command.startsWith("deadline")) {
            return CommandType.ADD_DEADLINE;
        } else if (command.startsWith("find")) {
            return CommandType.FIND;
        } else if (command.startsWith("unmark")) {
            return CommandType.UNMARK;
        } else if (command.startsWith("mark")) {
            return CommandType.MARK;
        } else if (command.startsWith("delete")) {
            return CommandType.DELETE;
        } else if (command.startsWith("list task")) {
            return CommandType.LIST_TASK;
        } else if (command.startsWith("note")) {
            return CommandType.ADD_NOTE;
        } else if (command.startsWith("list note")) {
            return CommandType.LIST_NOTE;
        } else {
            return null;
        }
    }

    private String handleCommand(CommandType commandType, String input) {
        switch (commandType) {
        case ADD_TODO:
            return handleAddTaskCommand(Command.TaskType.TODO, input);
        case ADD_DEADLINE:
            return handleAddTaskCommand(Command.TaskType.DEADLINE, input);
        case ADD_EVENT:
            return handleAddTaskCommand(Command.TaskType.EVENT, input);
        case FIND:
            return handleFindCommand();
        case UNMARK:
            return handleUnmarkCommand();
        case MARK:
            return handleMarkCommand();
        case DELETE:
            return handleDeleteCommand();
        case LIST_TASK:
            return handleListTaskCommand();
        case ADD_NOTE:
            return handleAddNoteCommand(input);
        case LIST_NOTE:
            return handleListNote();
        default:
            return "Unsupported command type.";
        }
    }

    /**
     * Handles the "bye" command, allowing the user to exit the application.
     */
    private void handleByeCommand() {
        String userMessage = userInput.getText();
        displayUserMessage(userMessage);

        String byeMessage = ui.showByeMessage();
        displayEchoBotMessage(byeMessage);

        userInput.clear();

        Duration delay = Duration.seconds(0.5);
        KeyFrame keyFrame = new KeyFrame(delay, event -> {
            Platform.exit();
            System.exit(0);
        });

        Timeline timeline = new Timeline(keyFrame);
        timeline.play();
    }

    /**
     * Handles the "list task" command, listing all tasks.
     *
     * @return A string containing the list of task.
     */
    private String handleListTaskCommand() {
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

        // Assert that taskNum is within valid bounds
        assert taskNum >= 1 && taskNum <= tasks.size() : "Task number is out of bounds";

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
    private String handleAddNoteCommand(String input) {
        String description = Command.extractDesc(input, "note");
        String[] noteParts = description.split("::", 2);
        if (noteParts.length < 2) {
            return "Please provide both a title and content for your note!";
        } else if (noteParts[1].trim().isEmpty()) {
            return "Please provide a content for your note!";
        } else {
            String title = noteParts[0].trim();
            String content = noteParts[1].trim();

            AddNoteCommand addNoteCommand = new AddNoteCommand(title, content);
            return addNoteCommand.doCommand(notes, storage, dialogContainer);
        }
    }

    /**
     * Handles the "list note" command, listing all saved notes.
     *
     * @return A response message listing all saved notes.
     */
    private String handleListNote() {
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

    private void displayUserMessage(String userMessage) {
        Label userTextLabel = new Label(userMessage);
        ImageView userImageView = new ImageView(user);
        DialogBox userDialogBox = DialogBox.getUserDialog(userTextLabel, userImageView);
        dialogContainer.getChildren().addAll(userDialogBox);
    }

}
