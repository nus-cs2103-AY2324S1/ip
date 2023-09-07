package duke.command;

import duke.Ui;
import duke.Storage;
import duke.task.*;

/**
 * Adds a deadline task to the taskList
 */
public class DeadlineCommand extends Command {
    protected String description;
    protected String by;
    protected boolean done;
    public DeadlineCommand(String description, String by, boolean done) {
        super();
        this.description = description;
        this.by = by;
        this.done = done;
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) {
        try {
            Task task = new Deadlines(description, by);
            task.setDone(this.done);
            taskList.addTask(task);
            return taskList.toString();
        } catch (Exception e) {
            return ui.format_response(e.getLocalizedMessage());
        }
    }
}
