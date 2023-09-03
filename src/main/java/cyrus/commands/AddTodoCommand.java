package cyrus.commands;

import cyrus.parser.ParseInfo;
import cyrus.tasks.TaskList;
import cyrus.tasks.ToDo;
import cyrus.ui.CliUi;

/**
 * Command to add a {@code ToDo} to the given {@code TaskList}.
 */
public class AddTodoCommand extends Command {
    public AddTodoCommand(TaskList taskList, ParseInfo parseInfo) {
        super(taskList, parseInfo);
    }

    /**
     * To add a {@code ToDo}, must ensure that the todo has an argument (i.e. name of the
     * deadline).
     *
     * @throws CommandError if no arguments are present.
     */
    @Override
    public void execute() throws CommandError {
        if (this.parseInfo.hasNoArgument()) {
            throw new CommandError("ToDo is missing a body!");
        }
        String content = String.join(" ", this.parseInfo.getArgument());
        ToDo todo = new ToDo(content);
        this.taskList.addTask(todo);
        CliUi.printAddTask(todo, this.taskList.size());
    }
}
