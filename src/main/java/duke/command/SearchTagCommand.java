package duke.command;

import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class SearchTagCommand extends Command{

    private String tag;

    public SearchTagCommand(String t) {
        tag = t;
    }

    @Override
    public String execute(TaskList list) {
        Task[] result = list.findTaskWithTag(tag);
        return Ui.instance.findTaskWithTagPrompt(result);
    }

}
