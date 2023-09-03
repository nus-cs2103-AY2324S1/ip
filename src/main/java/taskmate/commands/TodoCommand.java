package taskmate.commands;

import taskmate.tools.TaskList;
import taskmate.tools.Ui;
import taskmate.tools.Storage;
import taskmate.tools.tasks.Task;
import taskmate.tools.tasks.Todo;

public class TodoCommand extends AddCommand {

    String commandType;
    boolean isExit;

    public TodoCommand(String name) {
        this.commandType = "todo";
        this.isExit = false;
        this.name = name;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // 1. Create new to-do task
        Task newTodoTask = new Todo(name);

        // 2. Add to newTodoTask to tasks
        tasks.addTask(newTodoTask);

        // 3. Print successful task added message
        ui.printSuccessfulAddTaskResponse(newTodoTask, tasks.getNumTotalTasks());
    }

}
