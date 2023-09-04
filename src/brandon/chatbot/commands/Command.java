package commands;

import common.DukeException;
import common.DukeIndexOutOfBoundsException;
import common.DukeUnknownCommandException;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.Todo;

public class Command {
    public static final String LIST_SUCCESS = "ok... here is the list..";
    public static final String DELETE_SUCCESS = "ok... I'm deleting..";
    public static final String MARK_SUCCESS = "ok... I'm marking..";
    public static final String UNMARK_SUCCESS = "ok... I'm unmarking..";
    private String[] parsedCommand;
    protected TaskList tasks;
    public Command(TaskList tasks) {
        this.tasks = tasks;
    }

    protected Command () {

    }

    public CommandResult execute() throws Exception {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }
}
