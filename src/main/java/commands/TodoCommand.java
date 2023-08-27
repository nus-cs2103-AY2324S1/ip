package commands;

import errors.DotException;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

import java.io.File;

public class TodoCommand extends Command {
    private final String description;
    private final TaskList dotTaskList;
    private final String dataFilePathname;

    public TodoCommand(String description, TaskList dotTaskList,
                           String dataFilePathname) {
        this.description = description;
        this.dotTaskList = dotTaskList;
        this.dataFilePathname = dataFilePathname;
    }

    @Override
    public void execute() throws DotException {
        Task newTodoTask = new Todo(this.description);
        dotTaskList.addTask(newTodoTask);
        dotTaskList.saveTaskListToStorage(new File(this.dataFilePathname));
    }
}
