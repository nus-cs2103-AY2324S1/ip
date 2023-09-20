package brandon.chatbot.commands;

import brandon.chatbot.common.DukeException;
import brandon.chatbot.tasks.Todo;

/**
 * Represents a command that adds a todo task into the task list.
 */
public class AddTodoCommand extends Command {
    public static final String ADD_SUCCESS = "ok... I'm adding..";
    private Todo todoToAdd;

    public AddTodoCommand(String taskName) throws DukeException {
        this.todoToAdd = new Todo(taskName);
    }

    @Override
    public CommandResult execute() {
        assert this.todoToAdd != null: "Todo object should not be empty.";

        tasks.addTask(todoToAdd);
        CommandResult result = new CommandResult(ADD_SUCCESS);
        return result;
    }
}
