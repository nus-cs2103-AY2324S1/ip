package brandon.chatbot.commands;

import brandon.chatbot.common.DukeException;
import brandon.chatbot.tasks.Todo;

public class AddTodoCommand extends Command {
    public static final String ADD_SUCCESS = "ok... I'm adding..";
    private Todo toAdd;

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
