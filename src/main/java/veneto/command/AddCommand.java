package veneto.command;

import veneto.exceptions.VenetoException;
import veneto.exceptions.VenetoOperateException;
import veneto.task.*;

public class AddCommand extends Command {
    /** Fields */
    public static final String type = "add";
    private String text;
    private int id;
    private Task newTask;

    /** Constructor */
    public AddCommand(Task t) {
        super();
        this.newTask = t;
    }

    /** Methods */
    @Override
    public void op(TaskList tasks) throws VenetoException {
        try {
            tasks.add(newTask);
            tasks.storageChanged = 1;
        } catch (IndexOutOfBoundsException e) {
            throw new VenetoOperateException("Add");
        }
    }


    @Override
    public String toString() {
        return newTask.toString();
    }

    public static String getType() {
        return type;
    }
}
