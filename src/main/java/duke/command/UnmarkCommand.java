package duke.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
     */
    public void doCommand(ArrayList<Task> tasks, Ui ui, Storage storage) {
        if (taskNum >= 1 && taskNum <= tasks.size()) {
            Task task = tasks.get(taskNum - 1);

            task.unmark();

            Ui.showHorizontalLine();
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.print("      [" + task.getStatusIcon() + "] " + task.getDescription());

            if (task instanceof Event) {
                LocalDateTime from = ((Event) task).getFrom();
                LocalDateTime to = ((Event) task).getTo();
                System.out.print(" (from: " + from + " to: " + to + ")");
            } else if (task instanceof Deadline) {
                LocalDate by = ((Deadline) task).getBy();
                System.out.print(" (by: " + by + ")");
            }

            System.out.print("\n");

            Ui.showHorizontalLine();
            storage.saveTasks(tasks); // Save after unmarking
        }
    }
}

