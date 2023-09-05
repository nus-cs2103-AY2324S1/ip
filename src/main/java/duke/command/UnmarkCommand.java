package duke.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JTextArea;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int taskNum;

    /**
     * Constructs an UnmarkCommand instance.
     *
     * @param taskNum The task number to be unmarked.
     */
    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the UnmarkCommand, unmarking a task as not done and updating the task list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The data storage.
     * @param chatArea JTextArea for displaying messages in the GUI.
     */
    public void doCommand(ArrayList<Task> tasks, Ui ui, Storage storage, JTextArea chatArea) {
        if (taskNum >= 1 && taskNum <= tasks.size()) {
            Task task = tasks.get(taskNum - 1);

            task.unmark();

            chatArea.append("OK, I've marked this task as not done yet:\n");
            chatArea.append("[" + task.getStatusIcon() + "] " + task.getDescription());

            if (task instanceof Event) {
                LocalDateTime from = ((Event) task).getFrom();
                LocalDateTime to = ((Event) task).getTo();
                chatArea.append(" (from: " + from + " to: " + to + ")");
            } else if (task instanceof Deadline) {
                LocalDate by = ((Deadline) task).getBy();
                chatArea.append(" (by: " + by + ")");
            }

            chatArea.append("\n");

            storage.saveTasks(tasks, chatArea); // Save after unmarking
        }
    }
}

