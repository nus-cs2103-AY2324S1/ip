package ui;

import exceptions.BocchiException;
import gui.DialogBox;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import task.Task;
import task.TaskList;

/**
 * The User Interface (UI) class responsible for displaying messages and dialogues in the application.
 */
public class Ui {
    private static final String LINE_BREAK = "___________________________________________________";
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/larry.png"));
    private final Image bocchiImage = new Image(this.getClass().getResourceAsStream("/images/dorito.png"));
    private final VBox dialogContainer;

    /**
     * Constructs a Ui object with the specified dialog container.
     *
     * @param dialogContainer The container where dialog boxes will be displayed.
     */
    public Ui(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
    }

    /**
     * Outputs a dialogue box with the given message.
     *
     * @param message The message to be displayed.
     */
    private void outputDialogueBox(String message) {
        DialogBox dialogBox = DialogBox.getBocchiDialog(message, bocchiImage);
        dialogContainer.getChildren().add(dialogBox);
    }

    /**
     * Displays a greeting message when the application starts.
     */
    public void greet() {
        this.outputDialogueBox("Hello! I'm Bocchi\nWhat can I do for you?");
    }

    /**
     * Displays an exit message when the user exits the application.
     */
    public void exit() {
        this.outputDialogueBox("Bye. Hope to see you again soon!");
    }

    /**
     * Displays a message indicating successful loading of data.
     */
    public void loadSuccessful() {
        this.outputDialogueBox("Your previous data has made a return!");
    }

    /**
     * Displays a message indicating unsuccessful loading of data.
     */
    public void loadUnsuccessful() {
        this.outputDialogueBox("No previous data found");
    }

    /**
     * Displays a message indicating successful addition of a task.
     *
     * @param task     The task that was added.
     * @param taskList The updated task list.
     */
    public void addTaskSuccessful(Task task, TaskList taskList) {
        this.outputDialogueBox(
                String.format(
                        "Got it. I've added this task: %s\n"
                                + "Now you have %d tasks in the list.",
                        task, taskList.size()
                )
        );
    }

    /**
     * Displays a message indicating successful deletion of a task.
     *
     * @param task     The task that was deleted.
     * @param taskList The updated task list.
     */
    public void deleteTaskSuccessful(Task task, TaskList taskList) {
        this.outputDialogueBox(
                String.format(
                        "Noted. I've removed this task: %s\n"
                                + "Now you have %d tasks in the list.",
                        task, taskList.size()
                )
        );
    }

    /**
     * Displays a message indicating successful marking of a task as done.
     *
     * @param taskNumber The task number that was marked as done.
     * @param taskList   The updated task list.
     */
    public void markTaskSuccessful(int taskNumber, TaskList taskList) {
        this.outputDialogueBox(
                String.format(
                        "Nice! I've marked this task as done: %s",
                        taskList.getTask(taskNumber)
                )
        );
    }

    /**
     * Displays a message indicating successful unmarking of a task.
     *
     * @param taskNumber The task number that was unmarked.
     * @param taskList   The updated task list.
     */
    public void unmarkTaskSuccessful(int taskNumber, TaskList taskList) {
        this.outputDialogueBox(
                String.format(
                        "OK, I've marked this task as not done yet: %s",
                        taskList.getTask(taskNumber)
                )
        );
    }
    /**
     * Displays a list of tasks.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public void displayTasks(TaskList taskList) {
        this.outputDialogueBox(taskList.toString());
    }

    /**
     * Displays an exception message.
     *
     * @param e The exception containing the error message.
     */
    public void exception(BocchiException e) {
        this.outputDialogueBox(e.getMessage());
    }
}
