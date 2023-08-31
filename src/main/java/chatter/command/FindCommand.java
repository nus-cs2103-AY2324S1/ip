package chatter.command;

import chatter.Storage;
import chatter.TaskList;
import chatter.Ui;

/**
 * Represents a FindCommand class that is responsible for finding tasks via a keyword.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class FindCommand extends Command{
    /** Keyword to search for in tasks */
    private String keyword;

    /**
     * Constructor to initialize FindCommand class.
     *
     * @param keyword Keyword to find for in list of tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Searches for task with specific keyword.
     *
     * @param tasks chatter.TaskList class storing an ArrayList of chatter.task.Task objects.
     * @param ui chatter.Ui class to handle user interactions.
     * @param storage chatter.Storage class to read and store tasks by the user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFoundTasks(tasks, tasks.getNumOfTasks(), this.keyword);
    }
}
