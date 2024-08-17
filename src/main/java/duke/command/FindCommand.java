package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Command to find task with keyword.
 *
 * @author Lian Zhi Xuan
 */
public class FindCommand extends Command {

    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds task containing selected keyword.
     *
     * @param list TaskList to be modified.
     * @return list of task with the keyword.
     */
    @Override
    public String execute(TaskList list) {
        Task[] result = list.findTask(keyword);
        return Ui.instance.findPrompt(result);
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
