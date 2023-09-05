package duke;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;


/**
 * Represents the object that displays output from the chatbot.
 */
public class UI {
    private final Scanner scanner = new Scanner(System.in);
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image cortana = new Image(this.getClass().getResourceAsStream("/images/cortana.png"));

    /**
     * Displays user input in GUI.
     * @param input The user input
     * @param dialogContainer The GUI dialog container
     */
    public void displayUserInput(String input, VBox dialogContainer) {
        dialogContainer.getChildren().add(DialogBox.getUserDialogBox(input, user));
    }

    private void displayResponse(String response, VBox dialogContainer) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialogBox(response, cortana));
    }

    /**
     * Display greetings to user.
     */
    public void greet(VBox dialogContainer) {
        displayResponse("Hello I'm Cortana, Microsoft killed me so now I'm here. \n"
                + "What do you want me to do?", dialogContainer);
    }

    /**
     * Displays the current tasks list.
     * @param list The current tasks list
     */
    public void displayList(TaskList list, VBox dialogContainer) {
        if (list.isEmpty()) {
            displayResponse("There are no tasks in the list", dialogContainer);
        } else {
            displayResponse(list.toString(), dialogContainer);
        }
    }

    /**
     * Displays what task is added to the list.
     * @param task The task to be added
     * @param totalSize The total size of the list
     */
    public void displayAddToList(Task task, int totalSize, VBox dialogContainer) {
        String response = "";
        response += "This task is added to the list\n";
        response += task.toString();
        response += String.format("\nYou now have %d tasks in your list%n", totalSize);
        displayResponse(response, dialogContainer);
    }

    /**
     * Displays what task is removed from the list.
     * @param task The task to be removed
     * @param totalSize The total size of the list
     */
    public void displayRemoveFromList(Task task, int totalSize, VBox dialogContainer) {
        String response = "";
        response += "This task is deleted to the list\n";
        response += task.toString();
        response += String.format("\nYou now have %d tasks in your list%n", totalSize);
        displayResponse(response, dialogContainer);
    }

    /**
     * Displays what task is marked as done.
     * @param task The task to be marked as done
     */
    public void displayDoneTask(Task task, VBox dialogContainer) {
        String response = "";
        response += "This task is marked as done\n";
        response += task.toString();
        displayResponse(response, dialogContainer);
    }

    /**
     * Displays what task is marked as not done.
     * @param task The task to be marked as not done
     */
    public void displayNotDoneTask(Task task, VBox dialogContainer) {
        String response = "";
        response += "This task is marked as not done\n";
        response += task.toString();
        displayResponse(response, dialogContainer);
    }

    /**
     * Displays a specified list of tasks.
     * @param tasks The string representing the specified list of tasks
     */
    public void displayTasks(String tasks, VBox dialogContainer) {
        displayResponse(tasks, dialogContainer);
    }

    /**
     * Displays the DukeException
     * @param exception The DukeException to be displayed
     */
    public void displayException(DukeException exception, VBox dialogContainer) {
        displayResponse(exception.getMessage(), dialogContainer);
    }
}
