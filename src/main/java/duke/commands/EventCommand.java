package duke.commands;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;
import duke.tasks.Events;

/**
 * A command to called to initialize an event.
 */
public class EventCommand extends Command {
    protected TaskList taskList;
    protected Storage storage;
    protected Ui ui;
    protected String input;

    /**
     * Constructor for an event command.
     *
     * @param input The input for the command.
     * @param taskList The taskList to store the task.
     * @param storage The storage system to store the list.
     * @param ui The ui to print the commands.
     */
    public EventCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        this.input = input;
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Executes the method to create the event task.
     *
     * @throws DukeException Throws exception if there is an error parsing the date or saving the file.
     */
    @Override
    public void execute() throws DukeException {
        String task = "event";


        try {
            Events newTask = new Events(getDescription(task, input), getStartDate(input), getEndDate(task, input));
            taskList.addTask(newTask);
            ui.printAddTask(taskList, newTask);
            storage.save(taskList);
        } catch (IOException e) {
            ui.printError(e.getMessage());
        } catch (DateTimeParseException e) {
            ui.printInvalidTimeError();
        }
    }
}
