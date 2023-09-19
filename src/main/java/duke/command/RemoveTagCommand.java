package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;


/**
 * Command to remove tag from task.
 *
 * @author Lian Zhi Xuan
 */
public class RemoveTagCommand extends Command{

    private int index;
    private String tag;

    public RemoveTagCommand(int i, String t) {
        index = i;
        tag = t;
    }

    /**
     * Removes the given tag from the task.
     *
     * @param list TaskList to be modified.
     * @return prompt for removing the tag.
     */
    @Override
    public String execute(TaskList list) {
        list.removeTagFromTask(index,tag);
        Storage.instance.save(list);
        return Ui.instance.removeTagPrompt(list.getList().get(index));
    }
}
