package noac.command;

import noac.util.NoacException;
import noac.util.Storage;
import noac.util.TaskList;
import noac.util.Ui;

public class TagCommand extends Command{

    private int taskIndex;
    private String tag;

    /**
     * Creates the tag command class.
     *
     * @param taskIndex Index of the task to be tagged.
     * @param tag String to be tagged to the task.
     */
    public TagCommand(int taskIndex, String tag) {
        this.taskIndex = taskIndex;
        this.tag = tag;
    }

    /**
     * Tags the task and update the user, task list and save file.
     *
     * @param tasks List of all the task.
     * @param ui UI for printing result to user.
     * @param storage Storage class meant for saving to file.
     * @return String to be displayed to user.
     * @throws NoacException For any errors that needs to be displayed to user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NoacException {
        if (taskIndex + 1 > tasks.size() || taskIndex < 0) {
            throw new NoacException("OOPS!!! Please enter a task in your list!");
        }

        tasks.getTask(taskIndex).addTag(tag);

        storage.save(tasks);

        return ui.showTagTask(tasks.getTask(taskIndex), tag);
    }
}
