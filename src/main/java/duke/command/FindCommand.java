package duke.command;

import duke.Duke;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {

    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public String execute(TaskList list) {
        Task[] result = list.findTask(keyword);
        return Ui.ui.findPrompt(result);
    }

    public String getKeyword() {
        return keyword;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof FindCommand) {
            FindCommand temp = (FindCommand) o;
            return keyword.equals(temp.getKeyword());
        }
        return false;
    }
}
