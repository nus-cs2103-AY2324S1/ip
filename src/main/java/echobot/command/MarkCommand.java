package echobot.command;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import echobot.storage.Storage;
import echobot.task.Deadline;
import echobot.task.Event;
import echobot.task.Task;
import javafx.scene.layout.VBox;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command<Task> {
    private int taskNum;
    private String responseText;

    /**
     * Constructs a MarkCommand instance.
     *
     * @param taskNum The task number to be marked as done.
     */
    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String doCommand(ArrayList<Task> tasks, Storage storage, VBox dialogContainer) {
        if (taskNum <= 0) {
            responseText = "Sorry, the task doesn't exist.";
        } else if (taskNum >= 1 && taskNum <= tasks.size()) {
            Task task = tasks.get(taskNum - 1);

            task.mark();

            responseText = "Nice! I've marked this task as done:\n";
            responseText += "[" + task.getStatusIcon() + "] " + task.getDescription();
            getDateTimeInfo(task);
            assert storage != null : "Storage should not be null.";
            storage.saveTasks(tasks, dialogContainer); // Save after marking
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
