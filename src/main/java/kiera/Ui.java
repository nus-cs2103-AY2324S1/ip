package kiera;

import java.time.LocalDate;
import java.util.Scanner;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import kiera.task.Task;
import kiera.tasktype.TaskType;

/**
 * Displays messages and reads user input.
 */
public class Ui {
    private final String line = "   --------------------------------------------------------------";
    private VBox dialogBox;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/xbot.png"));
    private Image kiera = new Image(this.getClass().getResourceAsStream("/images/orange.png"));

    /**
     * Constructor for Ui.
     * @param dialogBox Box displaying user input or bot response.
     */
    public Ui(VBox dialogBox) {
        this.dialogBox = dialogBox;
        showHello();
    }

    /**
     * Shows an error message indicating that tasks cannot be loaded.
     */
    public void showLoadingError() {
        String output = "tasks cannot be loaded; starting with an empty list!";
        dialogBox.getChildren().addAll(DialogBox.getKieraDialog(
                output,
                kiera));
    }

    /**
     * Shows an error message with the specified content.
     *
     * @param e Error message to be displayed.
     */
    public void showError(String e) {
        dialogBox.getChildren().addAll(DialogBox.getKieraDialog(
                e,
                kiera));
    }

    /**
     * Displays a welcome message when the application starts.
     */
    public void showHello() {
        String output = "hi, it's kiera.\n"
                + "what do you need?";
        dialogBox.getChildren().addAll(DialogBox.getKieraDialog(
                output,
                kiera));
    }

    /**
     * Displays a farewell message when the user exits the application.
     */
    public void showBye() {
        String output = "muaks! <3";
        dialogBox.getChildren().addAll(DialogBox.getKieraDialog(
                output,
                kiera));
    }

    /**
     * Shows user input.
     * @param command Command that user inputs into the application.
     */
    public void showUserCommand(String command) {
        dialogBox.getChildren().addAll(DialogBox.getUserDialog(
                command,
                user));
    }

    /**
     * Shows a notice indicating that a task has been added.
     *
     * @param task Task that has been added.
     * @param t Type of task.
     * @param listSize Current size of the task list.
     */
    public void showAddNotice(Task task, TaskType t, int listSize) {
        String plural = listSize == 1 ? "task" : "tasks";
        String output = "alright, one "
                + t
                + " has been added: \n"
                + task
                + "\n"
                + listSize
                + " more "
                + plural
                + " to go!";
        dialogBox.getChildren().addAll(DialogBox.getKieraDialog(
                output,
                kiera));
    }

    /**
     * Shows a notice indicating that a task has been deleted.
     *
     * @param task Task to be deleted.
     * @param listSize Current size of the task list.
     */
    public void showDeleteNotice(Task task, int listSize) {
        String plural = listSize == 1 ? "task" : "tasks";
        String output = "alright, this task is gone: \n"
                + task
                + "\n"
                + listSize
                + " more "
                + plural
                + " left!";
        dialogBox.getChildren().addAll(DialogBox.getKieraDialog(
                output,
                kiera));
    }

    /**
     * Shows a notice listing filtered tasks based on date and task type.
     *
     * @param d Date to be filtered by.
     * @param t Type of tasks.
     * @param content String representation of filtered tasks.
     * @param listSize Size of the filtered task list.
     */
    public void showFilteredByDateNotice(LocalDate d, TaskType t, String content, int listSize) {
        String plural = listSize == 1 ? " " : "s ";
        String verb = listSize == 1 ? "is " : "are ";
        String output = "there "
                + verb
                + listSize
                + " "
                + t
                + plural
                + "due on "
                + d
                + ":\n"
                + content;
        dialogBox.getChildren().addAll(DialogBox.getKieraDialog(
                output,
                kiera));
    }

    /**
     * Shows a notice listing filtered tasks based on keyword.
     *
     * @param desc Keyword to be filtered by.
     * @param content String representation of filtered tasks.
     * @param listSize Size of the filtered task list.
     */
    public void showFilteredByKeywordNotice(String desc, String content, int listSize) {
        String plural = listSize == 1 ? " " : "s ";
        String verb = listSize == 1 ? "is " : "are ";
        String output = "there "
                + verb
                + listSize
                + " task"
                + plural
                + "that matches your keyword ("
                + desc
                + "):\n"
                + content;
        dialogBox.getChildren().addAll(DialogBox.getKieraDialog(
                output,
                kiera));
    }
    /**
     * Displays a list of tasks to the user.
     *
     * @param content Content of the task list to be displayed.
     */
    public void showList(String content) {
        String output = "you need to get these done:\n" + content;
        dialogBox.getChildren().addAll(DialogBox.getKieraDialog(
                output,
                kiera));
    }

    /**
     * Displays a list of tasks to the user after a mark or unmark command.
     *
     * @param content Content to be displayed.
     */
    public void showMarkedList(String notify, String content) {
        String output = notify + "\n" + "here's what you have to do now:\n" + content;
        dialogBox.getChildren().addAll(DialogBox.getKieraDialog(
                output,
                kiera));
    }
}
