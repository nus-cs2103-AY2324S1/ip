package taskmate.commands;

import taskmate.tools.Storage;
import taskmate.tools.TaskList;
import taskmate.tools.Ui;
import taskmate.tools.tasks.Deadline;
import taskmate.tools.tasks.Task;

import java.time.LocalDate;

/**
 * The DeadlineCommand class is the child class of the AddCommand class. It represents a "deadline" tasks given by the
 * user in the following form: "deadline <name> /by <date>".
 */
public class DeadlineCommand extends AddCommand {
    String commandType;
    boolean isExit;
    LocalDate by;

    /**
     * DeadlineCommand constructor that allows the developer to specify the name of the task, and a date that represents
     * the date that this task must be completed. This date must be a `LocalDate` instance.
     *
     * @param name the name of the deadline task.
     * @param by the date that the deadline task has to be completed.
     */
    DeadlineCommand(String name, LocalDate by) {
        this.commandType = "Deadline";
        this.isExit = false;
        this.name = name;
        this.by = by;
    }

    /**
     * DeadlineCommand constructor that allows the developer to specify the name of the task, and a date that represents
     * the date that this task must be completed. This date must be a String in the form "YYYY-mm-dd" to be parsed to a
     * `LocalDate` object.
     *
     * @param name the name of the deadline task.
     * @param by the date that the deadline task has to be completed. It has to be of the form "YYYY-mm-dd".
     */
    public DeadlineCommand(String name, String by) {
        this.commandType = "Deadline";
        this.isExit = false;
        this.name = name;
        this.by = LocalDate.parse(by);
    }

    /**
     * Executes the `deadline` command by the user by creating a new Deadline instance and adding it into the user's
     * task list, which is a TaskList instance `tasks`. Upon successful execution, a success message is printed by the `ui`
     * instance to inform the user about the successful execution.
     *
     * @param tasks TaskList object that stores the list of undeleted tasks defined by the user.
     * @param ui Ui object that deals with taking in user input and printing messages out to the user.
     * @param storage Storage object that saves undeleted tasks to the disk.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // 1. Create new deadline task
        Task newDeadlineTask = new Deadline(name, by);
        // 2. Add to newTodoTask to tasks
        tasks.addTask(newDeadlineTask);
        // 3. Print successful task added message
        ui.printSuccessfulAddTaskResponse(newDeadlineTask, tasks.getNumTotalTasks());
    }
}
