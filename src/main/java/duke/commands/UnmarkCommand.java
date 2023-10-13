package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.exception.WrongMarkException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to unmark a task
 */
public class UnmarkCommand extends Command {
    private int taskNumber;

    /**
     * Represents a constructor of the UnMarkCommand object
     */
    public UnmarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the UnMarkCommand and returns a string
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WrongMarkException {
        String output = "";
        try {
            Task task = tasks.get(taskNumber - 1);
            if (task.isItDone()) {
                task.setAsUndone();
                output += ui.unMarkTask(task);
            } else {
                ui.printLine();
                throw new WrongMarkException("This task is not done yet.");
            }
        } catch (IndexOutOfBoundsException e) {
            ui.printLine();
            output += "PIKA PIKA!!! Must choose something to unmark.";
            System.out.println("PIKA PIKA!!! Must choose something to unmark.");
        } catch (NullPointerException e) {
            ui.printLine();
            output += "PIKA PIKA!!! You chose air.";
            System.out.println("PIKA PIKA!!! You chose air.");
        }
        return output;
    }
}
