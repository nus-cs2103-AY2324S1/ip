package duke.command;

import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Command to search task with tag.
 *
 * @author Lian Zhi Xuan
 */
public class SearchTagCommand extends Command{

    private String tag;

    public SearchTagCommand(String t) {
        tag = t;
    }

    /**
     * Searches the task with the tag.
     *
     * @param list TaskList to be modified.
     * @return list of tasks that has the keyword.
     */
    @Override
    public String execute(TaskList list) {
        Task[] result = list.findTaskWithTag(tag);
        return Ui.instance.findTaskWithTagPrompt(result);
    }

}
