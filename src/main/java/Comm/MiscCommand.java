package Comm;
import Ui.Ui;
import Storage.TaskList;
import Storage.FileHandler;

/**
 * Represents a miscellaneous command.
 */
public class MiscCommand extends Command{

    /**
     * Constructs a `MiscCommand` object.
     */
    public MiscCommand(){
    }

    /**
     * Executes the miscellaneous command, displaying a message to the user indicating that the command is not understood.
     *
     * @param t  The task list (not used in this command).
     * @param ui The user interface to display the message.
     * @param f  The file handler (not used in this command).
     */
    @Override
    public void execute(TaskList t, Ui ui, FileHandler f) {
        ui.misc();
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