package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Duke is a simple task management program that allows users to add, delete, mark, and list tasks.
 */
public class Duke extends Application {
    private static TaskList taskList;

    /**
     * Greets the user with a welcome message.
     */
    public static void greet() {
        Ui.greet();
    }

    /**
     * Displays a farewell message when exiting the program.
     */
    public static void exit() {
        Ui.exit();
    }

    /**
     * Adds a task to the TaskList and displays a confirmation message.
     *
     * @param task The task to be added.
     */
    public static void add(Task task) {
        taskList.add(task);
        Ui.add(task, taskList.size());
    }

    /**
     * Deletes a task at the specified index from the TaskList and displays a confirmation message.
     *
     * @param index The index of the task to be deleted.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public static void delete(int index) {
        final Task task = taskList.get(index);
        taskList.delete(index);
        Ui.delete(task, taskList.size());
    }

    /**
     * Lists all tasks in the TaskList and displays them.
     */
    public static void list() {
        System.out.println(taskList);
    }

    /**
     * Lists tasks in the TaskList that match a given regex pattern and displays them.
     *
     * @param regex The regular expression pattern to match tasks against.
     */
    public static void listFiltered(String regex) {
        System.out.println(taskList.filteredToString(regex));
    }

    /**
     * Marks a task at the specified index as done and displays a confirmation message.
     *
     * @param index The index of the task to be marked as done.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public static void mark(int index) {
        taskList.mark(index);
        Ui.mark(taskList.get(index));
    }

    /**
     * Marks a task at the specified index as not done yet and displays a confirmation message.
     *
     * @param index The index of the task to be marked as not done yet.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public static void unmark(int index) {
        taskList.unmark(index);
        Ui.unmark(taskList.get(index));
    }

    /**
     * Runs the Duke program.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        final String DATA_DIRECTORY = "data";
        String projectRoot = System.getProperty("user.dir");
        String dataFilePath = projectRoot + "/" + DATA_DIRECTORY + "/tasks.ser";
        taskList = new TaskList(dataFilePath);

        Parser.start();
    }
    
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    
    @Override
    public void start(Stage stage) {
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

        // more code to be added here later
    }
}
