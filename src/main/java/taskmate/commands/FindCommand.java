package taskmate.commands;

import java.util.ArrayList;

import taskmate.tools.Storage;
import taskmate.tools.TaskList;
import taskmate.tools.Ui;
import taskmate.tools.tasks.Task;

/**
 * The FindCommand class is a child class of the Command class. It represents the "find `query`" command which looks
 * for any undeleted tasks whose descriptions (or dates) match `query`.
 */
public class FindCommand extends Command {

    private final String query;

    /**
     * Constructs a FindCommand object that allows the user to look for tasks that match `query`.
     */
    public FindCommand(String query) {
        this.query = query.toLowerCase();
        this.commandType = "Find";
        this.isExit = false;
    }

    /**
     * Executes the `find` command that matches the tasks in the user's undeleted tasks to the user-defined query. This
     * match is case-insensitive, and is able to match the tasks' names and/or the dates associated to them.
     * @param tasks TaskList object that stores the list of undeleted tasks defined by the user.
     * @param ui Ui object that deals with taking in user input and printing messages out to the user.
     * @param storage Storage object that saves undeleted tasks to the disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        assert this.query != null;

        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t : tasks.getAllTasks()) {
            boolean isMatchingTask = t.toString().toLowerCase().contains(this.query);
            if (isMatchingTask) {
                matchingTasks.add(t);
            }
        }
        ui.printMatchingTasks(matchingTasks);
    }
}
