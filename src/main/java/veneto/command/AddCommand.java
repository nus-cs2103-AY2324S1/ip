package veneto.command;

import veneto.exceptions.VenetoException;
import veneto.exceptions.VenetoOperateException;
import veneto.task.*;

public class AddCommand extends Command {
    /* Fields */
    public static final String type = "add";
    private Task newTask;

    /* Constructor */

    public AddCommand(Task t) {
        super();
        this.newTask = t;
    }

    /* Methods */
    /**
     * the AddCommand operates
     * @param tasks the TaskList where the AddCommand put new task
     */
    @Override
    public void op(TaskList tasks) throws VenetoException {
        try {
            if (!isDuplicated(tasks)) {
                tasks.add(newTask);
                tasks.storageChanged = 1;
            } else {
                throw new VenetoOperateException("Duplicate");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new VenetoOperateException("Add");
        }
    }


    @Override
    /**
     * @return the String representation of the task added to the TaskList
     */
    public String toString() {
        return newTask.toString();
    }

    /**
     * @return the type of the Command
     */
    public String getType() {
        return type;
    }

    private boolean isDuplicated(TaskList tasks) {
        boolean hasSameTask = false;
        for (Task currTask : tasks) {
            if (currTask.equals(newTask)) {
                hasSameTask = true;
                break;
            }
        }
        return hasSameTask;
    }
}
