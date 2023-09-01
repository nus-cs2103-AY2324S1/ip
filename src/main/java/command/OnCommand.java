package command;

import duke.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class OnCommand extends Command{
    /**
     * Command to search for tasks on a date.
     */
    public static final String COMMAND_ON = "on";

    /**
     * Constructor for the OnCommand class.
     *
     * @param params Parsed user input.
     */
    public OnCommand(ArrayList<String> params) throws InvalidCommandException {
        super(params);
        if (params.size() != 2) {
            throw new InvalidCommandException("Add todo command format is wrong");
        }
    }

    /**
     * Executes the command.
     *
     * @param tasks List of tasks.
     * @param ui UI of the application.
     * @param storage Object to handle data storage.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        LocalDate date = LocalDate.parse(params.get(1));
        ArrayList<Task> tasksOnDate = tasks.getTasksOn(date);
        ui.printTasksOn(tasksOnDate);
    }

    /**
     * Returns a boolean representing whether the command requires the application to exit.
     *
     * @return Boolean representing whether the command exits the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
