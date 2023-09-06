package brandon.chatbot.commands;

import brandon.chatbot.common.DukeException;
import brandon.chatbot.common.DukeIndexOutOfBoundsException;
import brandon.chatbot.common.DukeUnknownCommandException;
import brandon.chatbot.tasks.Deadline;
import brandon.chatbot.tasks.Event;
import brandon.chatbot.tasks.TaskList;
import brandon.chatbot.tasks.Todo;

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
