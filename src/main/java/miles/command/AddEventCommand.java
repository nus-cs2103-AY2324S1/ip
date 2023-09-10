package miles.command;

import miles.MilesException;
import miles.Storage;
import miles.TaskList;
import miles.Ui;
import miles.task.Event;

public class AddEventCommand extends Command {
    private String input;

    /**
     * Constructor to create a new event command.
     * @param input user input
     */
    public AddEventCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Event newEvent = new Event(this.input);
            storage.saveWhenAddTask(newEvent, taskList);

            int n = taskList.getSize();
            ui.printAddedTask(newEvent, n);
        } catch (MilesException e) {
            ui.printErrorMsg(e.getMessage());
        }
    }
}
