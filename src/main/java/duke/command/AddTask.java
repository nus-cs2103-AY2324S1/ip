package duke.command;

import duke.TaskList;
import duke.Ui;

/** Abstraction of a command to add a task to a list. */
public abstract class AddTask extends Command {

    protected Ui ui = new Ui();
    protected TaskList list;

    protected String specifications;

    /**
     * Creates a command to add task to a list.
     *
     * @param list List that task will be added to.
     * @param specifications Description of the task.
     */
    public AddTask(TaskList list, String specifications) {
        this.list = list;
        this.specifications = specifications;
    }
}
