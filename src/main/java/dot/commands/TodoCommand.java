package dot.commands;

import dot.errors.DotException;
import dot.tasks.Task;
import dot.tasks.TaskList;
import dot.tasks.Todo;

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
