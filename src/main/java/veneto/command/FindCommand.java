package veneto.command;

import veneto.exceptions.VenetoException;
import veneto.exceptions.VenetoOperateException;
import veneto.task.Task;
import veneto.task.TaskList;

public class FindCommand extends Command{
    /* Fields */
    public static final String type = "find";
    private String keyword;
    private TaskList foundTasks;

    /* Constructor */
    /**
     * creates a find command
     * @param keyword contained in the task which should be found
     */
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
        this.foundTasks = new TaskList();
    }

    /* Methods */
    /**
     * the FindCommand operates
     * @param tasks the TaskList where the Command search from
     * @throws VenetoException
     */
    @Override
    public void op(TaskList tasks) throws VenetoException {
        Task currTask;
        for (int i = 0; i < tasks.size(); i++) {
            currTask = tasks.get(i);
            if (currTask.contains(keyword)) {
                foundTasks.add(currTask);
            }
        }
        if (foundTasks.isEmpty()) {
            throw new VenetoOperateException("Not Found");
        }
    }

    /**
     * @return the tasks found
     */
    @Override
    public String toString() {
        return foundTasks.toString();
    }

    /**
     * @return the type of the Command
     */
    public String getType() {
        return type;
    }
}
