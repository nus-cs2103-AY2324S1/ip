package brandon.chatbot.commands;

import brandon.chatbot.common.DukeException;
import brandon.chatbot.common.DukeUnknownCommandException;
import brandon.chatbot.tasks.Deadline;
import brandon.chatbot.tasks.Event;
import brandon.chatbot.tasks.TaskList;
import brandon.chatbot.tasks.Todo;

public class AddTodoCommand extends Command {
    private Todo toAdd;
    public static final String ADD_SUCCESS = "ok... I'm adding..";

    public AddTodoCommand(String taskName) throws DukeException {
        this.toAdd = new Todo(taskName);
    }

    @Override
    public CommandResult execute() {
        tasks.addTask(toAdd);
        CommandResult result = new CommandResult(ADD_SUCCESS);
        return result;
    }
}
