package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Command to create a assign tag.
 *
 * @author Lian Zhi Xuan
 */
public class AssignTagCommand extends Command{

    private String tag;

    private int index;

    public AssignTagCommand(int i, String t) {
        index = i;
        tag = t;
    }

    /**
     * Assigns tag to selected task.
     *
     * @param list TaskList to be modified.
     * @return prompt when assigning tag.
     */
    @Override
    public String execute(TaskList list) {
        list.assignTaskWithTag(index,tag);
        Storage.instance.save(list);
        return Ui.instance.assignTagPrompt(list.getList().get(index));
    }
}
