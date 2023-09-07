package ui;

import exceptions.BocchiException;
import gui.DialogBox;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import task.Task;
import task.TaskList;

public class Ui {
    private static final String LINE_BREAK = "___________________________________________________";
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/larry.png"));
    private final Image bocchiImage = new Image(this.getClass().getResourceAsStream("/images/dorito.png"));
    private final VBox dialogContainer;

    public Ui(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
    }

    private void outputDialogueBox(String message) {
        DialogBox dialogBox = DialogBox.getBocchiDialog(message, bocchiImage);
        dialogContainer.getChildren().add(dialogBox);
    }

    /**
     * Outputs greeting message.
     */
    public void greet() {
        this.outputDialogueBox("Hello! I'm bocchi.Bocchi\nWhat can I do for you?");
    }

    /**
     * Outputs exit message.
     */
    public void exit() {
        this.outputDialogueBox("Bye. Hope to see you again soon!");
    }

    /**
     * Outputs successful loading of data message.
     */
    public void loadSuccessful() {
        this.outputDialogueBox("Previous data has been loaded");
    }

    /**
     * Outputs unsuccessful loading of data message.
     */
    public void loadUnsuccessful() {
        this.outputDialogueBox("No previous data found");
    }

    /**
     * Outputs successful addition of task message.
     */
    public void addTaskSuccessful(Task task, TaskList taskList) {
        this.outputDialogueBox(
                String.format(
                        "Got it. I've added this task: %s\n" +
                                "Now you have %d tasks in the list.",
                        task, taskList.size()
                )
        );
    }

    /**
     * Outputs successful deletion of task message.
     */
    public void deleteTaskSuccessful(Task task, TaskList taskList) {
        this.outputDialogueBox(
                String.format(
                        "Noted. I've removed this task: %s\n" +
                                "Now you have %d tasks in the list.",
                        task, taskList.size()
                )
        );
    }

    /**
     * Outputs successful marking of task message.
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
     * Outputs successful unmarking of task message.
     */
    public void unmarkTaskSuccessful(int taskNumber, TaskList taskList) {
        this.outputDialogueBox(
                String.format(
                        "OK, I've marked this task as not done yet: %s",
                        taskList.getTask(taskNumber)
                )
        );
    }

    public void displayTasks(TaskList taskList) {
        this.outputDialogueBox(taskList.toString());
    }

    public void exception(BocchiException e) {
        this.outputDialogueBox(e.getMessage());
    }
}
