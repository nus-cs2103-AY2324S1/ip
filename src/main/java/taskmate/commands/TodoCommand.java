package taskmate.commands;

import taskmate.tools.TaskList;
import taskmate.tools.Ui;
import taskmate.tools.Storage;
import taskmate.tools.tasks.Task;
import taskmate.tools.tasks.Todo;

/**
 * The TodoCommand class is the child class of the AddCommand class. It represents a "todo" tasks given by the user in
 * the following form: "todo <name>".
 */
public class TodoCommand extends AddCommand {

    String commandType;
    boolean isExit;

    /**
     * TodoCommand constructor that allows the developer to specify the name of the task.
     *
     * @param name the name of the todo task.
     */
    public TodoCommand(String name) {
        this.commandType = "todo";
        this.isExit = false;
        this.name = name;
    }

    /**
     * Executes the `todo` command by the user by creating a new Todo instance and adding it into the user's task
     * list, which is a TaskList instance `tasks`. Upon successful execution, a success message is printed by the `ui`
     * instance to inform the user about the successful execution.
     *
     * @param tasks TaskList object that stores the list of undeleted tasks defined by the user.
     * @param ui Ui object that deals with taking in user input and printing messages out to the user.
     * @param storage Storage object that saves undeleted tasks to the disk.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // 1. Create new to-do task
        Task newTodoTask = new Todo(name);
        // 2. Add to newTodoTask to tasks
        tasks.addTask(newTodoTask);
        // 3. Print successful task added message
        ui.printSuccessfulAddTaskResponse(newTodoTask, tasks.getNumTotalTasks());
    }

}
