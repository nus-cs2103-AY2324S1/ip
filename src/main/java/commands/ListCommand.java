package commands;

import functional.TaskList;
import functional.Ui;
import tasks.Task;

/**
 * A command to list the tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructs a `ListCommand` object.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the list command, which displays all the tasks in the task list.
     *
     * @param tasks  The task list to retrieve tasks from.
     * @param ui The user interface to display the list of tasks.
     * @param marked always false - intended for use by AddCommand
     * @param load always false - intended for use by AddCommand
     */
    public void execute(TaskList tasks, Ui ui, boolean marked, boolean load) {
        System.out.println("____________________________________________________________\n" +
                "Here are the tasks in your list");
        for (int i = 0; i < tasks.size(); i++) {
            Task job = (Task) tasks.get(i);
            System.out.println(String.format("%d. %s", i + 1, job.toString()));
        }
        System.out.println("____________________________________________________________");
    }
}
