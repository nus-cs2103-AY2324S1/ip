package avalon;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * The main class for the Duke ChatBot (Avalon).
 */
public class Avalon extends Application{

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs an Avalon instance with the specified file path for task storage.
     */
    public Avalon() {
        this.ui = new Ui();
        this.storage = new Storage("src/main/data/Avalon.txt");
        this.tasks = new TaskList();
        storage.loadTasks(this.tasks);
    }

    /**
     * Runs the Avalon application, allowing users to manage tasks using a command-line interface.
     */
    public void run() {
        int taskIndex;
        String description;
        String[] parts;

        ui.greetMessage();

        while (true) {
            String userInput = ui.getUserInput();
            String[] inputStr = userInput.split(" ");
            String command = inputStr[0];

            try {

                // Handle various commands
                switch (command) {
                case "bye":
                    storage.saveTasks(tasks);
                    ui.byeMessage();
                    return;
                case "list":
                    ui.showTasksList(tasks);
                    break;
                case "mark":
                    taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        tasks.get(taskIndex).markDone();
                        ui.showMarkMessage(tasks, taskIndex);
                    } else {
                        throw new IllegalArgumentException("Invalid task number to be marked.");
                    }
                    break;
                case "unmark":
                    taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        tasks.get(taskIndex).markNotDone();
                        ui.showUnmarkMessage(tasks, taskIndex);
                    } else {
                        throw new IllegalArgumentException("Invalid task number to be unmarked.");
                    }
                    break;
                case "todo":
                    description = userInput.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new IllegalArgumentException("The description of a todo cannot be empty.");
                    }

                    ToDo todo = new ToDo(description);
                    tasks.addTask(todo);
                    ui.showAddTaskMessage(tasks);
                    break;
                case "deadline":
                    parts = userInput.substring(9).split(" /by ");
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Please provide a description and a deadline       (use /by).");
                    }
                    description = parts[0];
                    String by = parts[1];

                    Deadline deadline = new Deadline(description, by);
                    tasks.addTask(deadline);
                    ui.showAddTaskMessage(tasks);
                    break;
                case "event":
                    parts = userInput.substring(6).split(" /from | /to ");
                    if (parts.length != 3) {
                        throw new IllegalArgumentException("Please provide a description, a starting time,     and an ending time (use /from and /to).");
                    }
                    description = parts[0];
                    String from = parts[1];
                    String to = parts[2];
                    Event event = new Event(description, from, to);
                    tasks.addTask(event);
                    ui.showAddTaskMessage(tasks);
                    break;
                case "delete":
                    taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        Task deletedTask = tasks.get(taskIndex);
                        tasks.removeTask(taskIndex);
                        ui.showDeleteTaskMessage(tasks, deletedTask);
                    } else {
                        throw new IllegalArgumentException("Invalid task number to be deleted.");
                    }
                case "find":
                    String keyword = userInput.substring(5).trim();
                    findTasksByKeyword(keyword);
                    break;
                default:
                    throw new IllegalArgumentException("I humbly apologize, but thy words remain a mystery to me...");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("☹ OOPS!!! " + e.getMessage());
            }
        }

    }

    /**
     * Searches for tasks containing the specified keyword in their descriptions and displays the matching tasks.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    private void findTasksByKeyword(String keyword) {
        TaskList matchingTasks = new TaskList();

        for (Task task : tasks.tasks()) {
            if (task.description.contains(keyword)) {
                matchingTasks.addTask(task);
            }
        }

        ui.showFindMessage(matchingTasks);
    }

    /** The entry point of the Avalon application.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Create an instance of Avalon and start the application
        new Avalon().run();
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

    private String getResponse(String input) {
        return "Arthur reiterates: " + input;
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

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Avalon");
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
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }
}
