package duke.command;

import duke.TaskList;
import duke.Ui;

/** Abstraction for marking a task as completed. */
public class Mark extends Command {

    private Ui ui = new Ui();
    private TaskList list;
    private String specifications;

    /**
     * Creates a Mark command that will tell the user that the task has been marked.
     *
     * @param list The list containing the task.
     * @param specifications Index of the task to be marked.
     */
    public Mark(TaskList list, String specifications) {
        this.list = list;
        this.specifications = specifications;
    }

    @Override
    public String execute() {
        if (specifications.isEmpty()) {
            throw new IllegalArgumentException("Please indicate task number.");
        }
        int index = Integer.parseInt(specifications) - 1;
        return this.list.mark(index);
    }
}
