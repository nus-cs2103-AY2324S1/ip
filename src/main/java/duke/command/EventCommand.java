package duke.command;

import duke.Ui;
import duke.command.Command;
import duke.Storage;
import duke.task.*;

/**
 * Adds an event task to the taskList
 */
public class EventCommand extends Command {
    protected String description;
    protected String from;
    protected String to;
    protected boolean done;
    public EventCommand(String description, String from, String to, boolean done) {
        super();
        this.description = description;
        this.from = from;
        this.to = to;
        this.done = done;
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) {
        try {
            Task task = new Event(description, from, to);
            task.setDone(done);
            taskList.addTask(task);
            return taskList.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
