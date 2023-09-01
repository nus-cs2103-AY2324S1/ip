package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

    
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
