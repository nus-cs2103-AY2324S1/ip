package duke.command;
import duke.task.*;
import duke.helper.*;
public class ExitCommand extends Command{
    /**
     * execute for ExitCommand does not execute anything
     * @param tasks takes in TaskList of tasks in MeowBot
     * @param ui the same object reference for the UI class
     * @param store reference
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) {

    }

    /**
     * method to indicate that ExitCommand has been executed
     * @return true as this is in the ExitCommand class
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
