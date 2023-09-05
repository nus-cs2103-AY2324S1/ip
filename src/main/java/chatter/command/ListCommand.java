package chatter.command;

import chatter.Storage;
import chatter.TaskList;
import chatter.Ui;

/**
 * Represents a chatter.command.Command class that is responsible for listing the tasks in the list of tasks.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class ListCommand extends Command {
    /**
     * Constructor for the chatter.command.ListCommand class for users to list all the tasks.
     */
    public ListCommand() {
        super();
    }

    /**
     * Lists all the tasks.
     *
     * @param tasks chatter.TaskList class storing an ArrayList of chatter.task.Task objects.
     * @param ui chatter.Ui class to handle user interactions.
     * @param storage chatter.Storage class to read and store tasks by the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showListTasks(tasks, tasks.getNumOfTasks());
    }
}
