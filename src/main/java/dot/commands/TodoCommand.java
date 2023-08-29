package commands;

import errors.DotException;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

public class TodoCommand extends Command {
    private final String description;
    private final TaskList dotTaskList;

    public TodoCommand(String description, TaskList dotTaskList) {
        this.description = description;
        this.dotTaskList = dotTaskList;
    }

    @Override
    public void execute() throws DotException {
        Task newTodoTask = new Todo(this.description);
        dotTaskList.addTask(newTodoTask);
        dotTaskList.saveTaskListToStorage();
    }
}
