package echobot.command;

import java.util.ArrayList;

import echobot.storage.Storage;
import echobot.task.Deadline;
import echobot.task.Event;
import echobot.task.Task;
import echobot.ui.Ui;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command<Task> {
    private int taskNum;
    private String responseText;

    /**
     * Constructs an UnmarkCommand instance.
     *
     * @param taskNum The task number to be unmarked.
     */
    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Marks a task as not done based on the given task number, gives the response message,
     * and saves the updated task list to storage.
     *
     * @param tasks           The list of tasks.
     * @param ui              The user interface component.
     * @param storage         The storage component for saving tasks.
     * @param scene           The JavaFX scene.
     * @param dialogContainer The container for displaying dialog messages.
     * @return The response message indicating the task was marked as not done.
     */
    public String doCommand(ArrayList<Task> tasks, Ui ui, Storage storage, Scene scene, VBox dialogContainer) {
        if (taskNum >= 1 && taskNum <= tasks.size()) {
            Task task = tasks.get(taskNum - 1);

            task.unmark();

            responseText = "OK, I've marked this task as not done yet:\n";
            responseText += "[" + task.getStatusIcon() + "] " + task.getDescription();

            if (task instanceof Event) {
                responseText += " (from: " + ((Event) task).getFrom() + " to: " + ((Event) task).getTo() + ")";
            } else if (task instanceof Deadline) {
                responseText += " (by: " + ((Deadline) task).getBy() + ")";
            }

            storage.saveTasks(tasks, dialogContainer); // Save after unmarking
        }

        return responseText;
    }

    public String getResponse() {
        return responseText;
    }
}

