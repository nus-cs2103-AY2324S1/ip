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
    public void execute(TaskList list) {
        Task[] result = list.findTask(keyword);
        Ui.ui.findPrompt(result);
        Duke.run();
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
