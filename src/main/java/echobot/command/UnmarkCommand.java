package echobot.command;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import echobot.storage.Storage;
import echobot.task.Deadline;
import echobot.task.Event;
import echobot.task.Task;
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
     * @param storage         The storage component for saving tasks.
     * @param dialogContainer The container for displaying dialog messages.
     * @return The response message indicating the task was marked as not done.
     */
    public String doCommand(ArrayList<Task> tasks, Storage storage, VBox dialogContainer) {
        if (taskNum <= 0) {
            responseText = "Sorry, the task doesn't exist.";
        } else if (taskNum >= 1 && taskNum <= tasks.size()) {
            Task task = tasks.get(taskNum - 1);

            task.unmark();

            responseText = "OK, I've marked this task as not done yet:\n";
            responseText += "[" + task.getStatusIcon() + "] "
                    + task.getDescription();
            getDateTimeInfo(task);

            storage.saveTasks(tasks, dialogContainer); // Save after unmarking
        } else {
            responseText = "Sorry, you only have " + tasks.size() + " tasks in your list.\n";
        }

        return responseText;
    }

    private String getDateTimeInfo(Task task) {
        // Use type casting for specific task types to access additional information
        if (task instanceof Event) {
            responseText += " (from: "
                    + ((Event) task).getStart().format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                    + " to: "
                    + ((Event) task).getEnd().format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                    + ")";
        } else if (task instanceof Deadline) {
            responseText += " (by: "
                    + ((Deadline) task).getDueDate().format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }

        return responseText;
    }

    public String getResponse() {
        return responseText;
    }
}

