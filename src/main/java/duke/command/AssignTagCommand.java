package duke.command;

import duke.Ui;
import duke.task.TaskList;

public class AssignTagCommand extends Command{

    private String tag;

    private int index;

    public AssignTagCommand(int i, String t) {
        index = i;
        tag = t;
    }

    @Override
    public String execute(TaskList list) {
        list.assignTaskWithTag(index,tag);
        return Ui.instance.assignTagPrompt(list.getList().get(index));
    }
}
