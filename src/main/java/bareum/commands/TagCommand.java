package bareum.commands;

import bareum.BareumException;
import bareum.Storage;
import bareum.TaskList;
import bareum.Ui;

/**
 * This class implements the command for tagging a task.
 */
public class TagCommand extends Command {
    /**
     * Index of task to tag.
     */
    private int index;

    /**
     * Tag to be added to task.
     */
    private String tag;

    /**
     * Create a new instance of a command that tags the task at the corresponding index when executed.
     *
     * @param index Index of task to tag.
     * @param tag Tag the user wants to tag the task with.
     */
    public TagCommand(int index, String tag) {
        this.index = index;
        this.tag = tag;
    }


    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws BareumException {
        taskList.tag(index, tag);
        storage.saveAllTasks(taskList);

        return "Ok! I've tagged this task:\n" + taskList.get(index).toString();
    }
}
