package duke.command;

import duke.Task;
import duke.TaskList;
import duke.Ui;

/** Abstraction to delete a task from a list. */
public class DeleteTask extends Command {

    private Ui ui = new Ui();
    private TaskList list;
    private String specifications;

    /**
     * Creates a DeleteTask command.
     *
     * @param list List containing the task to delete.
     * @param specifications Index of task to be deleted.
     */
    public DeleteTask(TaskList list, String specifications) {
        this.list = list;
        this.specifications = specifications;
    }

    @Override
    public String execute() {
        if (specifications.isEmpty()) {
            throw new IllegalArgumentException("Please indicate task number.");
        }
        try {
            int number = Integer.parseInt(specifications);
            Task taskToRemove = this.list.retrieve(number - 1);
            this.list.delete(number - 1);
            return this.ui.showTaskDeleted(taskToRemove, this.list.length());
        } catch (IndexOutOfBoundsException error) {
            throw new IllegalArgumentException("OOPS!!! I could not find any task in that position.");
        }
    }
}
