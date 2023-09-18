package barbie.commands;

import java.util.ArrayList;

import barbie.Ui;
import barbie.types.Task;

/**
 * Represents the command when a "list" is called by the user.
 */
public class ListCommand extends Command {

    /**
     * Constructs an instance of a ListCommand.
     */
    public ListCommand() {
        this.isExit = false;

    }

    /**
     * Runs the command of this instance of Command.
     * (eg ExitCommand will contain the logic of exiting the application)
     * @param taskList current list of tasks
     * @return String to be returned to user
     */
    @Override
    public String run(ArrayList<Task> taskList) {
        try {
            return Ui.listTasks(taskList);
        } catch (Exception e) {
            return e.getMessage();
        }
    }


}
