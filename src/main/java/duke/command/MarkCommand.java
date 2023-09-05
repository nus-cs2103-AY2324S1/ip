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
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int taskNum;

    /**
     * Constructs a MarkCommand instance.
     *
     * @param taskNum The task number to be marked as done.
     */
    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the MarkCommand, marking a task as done and updating the task list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The data storage.
     * @param chatArea JTextArea for displaying messages in the GUI.
     */
    @Override
    public void doCommand(ArrayList<Task> tasks, Ui ui, Storage storage, JTextArea chatArea) {
        if (taskNum >= 1 && taskNum <= tasks.size()) {
            Task task = tasks.get(taskNum - 1);

            task.mark();
            chatArea.append("Nice! I've marked this task as done:\n");
            chatArea.append("[" + task.getStatusIcon() + "] " + task.getDescription());

            // Additional information
            if (task instanceof Event) {
                LocalDateTime from = ((Event) task).getFrom();
                LocalDateTime to = ((Event) task).getTo();
                chatArea.append(" (from: " + from + " to: " + to + ")");
            } else if (task instanceof Deadline) {
                LocalDate by = ((Deadline) task).getBy();
                chatArea.append(" (by: " + by + ")");
            }

            chatArea.append("\n");

            storage.saveTasks(tasks, chatArea); // Save after marking
        }
    }
}
