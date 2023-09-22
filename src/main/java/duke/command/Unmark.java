package duke.command;

import duke.TaskList;
import duke.Ui;

/** Abstraction of a command to unmark a task of a list. */
public class Unmark extends Command {

    private Ui ui = new Ui();
    private TaskList list;
    private String specifications;

    /**
     * Creates a command to mark a task as incomplete.
     *
     * @param list List containing task to be unmarked.
     * @param specifications Index of the task.
     */
    public Unmark(TaskList list, String specifications) {
        this.list = list;
        this.specifications = specifications;
    }

    @Override
    public String execute() {
        if (specifications.isEmpty()) {
            throw new IllegalArgumentException("Please indicate task number.");
        }
        int index = Integer.parseInt(specifications) - 1;
        return this.list.unmark(index);
    }
}
