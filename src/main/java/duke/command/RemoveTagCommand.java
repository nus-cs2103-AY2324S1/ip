package duke.command;

import duke.Ui;
import duke.task.TaskList;

public class RemoveTagCommand extends Command{

    private int index;
    private String tag;

    public RemoveTagCommand(int i, String t) {
        index = i;
        tag = t;
    }

    @Override
    public String execute(TaskList list) {
        list.removeTagFromTask(index,tag);
        return Ui.instance.removeTagPrompt(list.getList().get(index));
    }
}
