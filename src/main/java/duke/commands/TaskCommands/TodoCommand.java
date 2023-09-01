package duke.commands.TaskCommands;

import java.util.regex.Matcher;

import duke.TaskListStorage;
import duke.commands.TaskCommand;
import duke.exceptions.MissingDescriptionException;
import duke.tasks.Todo;

public class TodoCommand extends TaskCommand {
    public TodoCommand(Matcher matcher) throws MissingDescriptionException {
        super(matcher);
    }

    @Override
    public void execute(TaskListStorage taskListStorage) throws MissingDescriptionException {
        taskListStorage.addTodo(new Todo(this.description));
    }
}