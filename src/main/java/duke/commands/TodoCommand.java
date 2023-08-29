package duke.commands;

import duke.TaskList;
import duke.tasks.Todo;

public class TodoCommand extends Command {
    private final String args;

    public TodoCommand(String args) {
        this.args = args;
    }

    @Override
    public CommandResult run(TaskList tasks) throws CommandException {
        if (args.isEmpty()) {
            throw new CommandException("Todo description cannot be empty!");
        }

        Todo todo = new Todo(args);
        tasks.add(todo);

        return new CommandResult(true, "Got it. I've added this task:", todo.toString(), String.format("Now you have %d %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks"));
    }
}
