package Comm;
import Ui.Ui;
import Storage.TaskList;
import Storage.FileHandler;

/**
 * An error command.
 */
public class ErrorCommand extends Command{

    /**
     * Constructs an `ErrorCommand` object.
     */
    public ErrorCommand(){
    }

    /**
     * Executes the error command
     *
     * @param t  The task list (not used in this command).
     * @param ui The user interface. (not used in this command).
     * @param f  The file handler (not used in this command).
     */
    @Override
    public void execute(TaskList t, Ui ui, FileHandler f) {

    }

    /**
     * Check whether the command is an exit command.
     *
     * @return `false` because this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}