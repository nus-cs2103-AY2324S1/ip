package taskmate.commands;

import taskmate.tools.TaskList;
import taskmate.tools.Ui;
import taskmate.tools.Storage;

/**
 * The ListCommand class is a child class of the Command class. It represents the `list` command which allows the
 * user to view the list of undeleted tasks, the status of each task (i.e. whether the task has been marked as
 * completed), and the type of each task (i.e. Todo, Deadline, or Event).
 */
public class ListCommand extends Command {

    String commandType;
    boolean isExit;

    /**
     * ListCommand constructor creates a `list` command that provides information about each task.
     */
    public ListCommand() {
        this.commandType = "List";
        this.isExit = false;
    }

    /**
     * Executes the `list` command by the user by printing out the list of undeleted tasks, the status of each task (i.e.
     *  * whether the task has been marked as completed), and the type of each task (i.e. Todo, Deadline, or Event).
     *
     * @param tasks TaskList object that stores the list of undeleted tasks defined by the user.
     * @param ui Ui object that deals with taking in user input and printing messages out to the user.
     * @param storage Storage object that saves undeleted tasks to the disk.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printAllTasks(tasks);
    }
}
