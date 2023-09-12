package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.exceptions.WrongMarkException;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class UnmarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructor of the UnMarkCommand object
     */
    public UnmarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Execute the UnMarkCommand and returns a string
     * 
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WrongMarkException {
        String output = "";
        Task task = tasks.get(taskNumber - 1);
        try {
            if (task.isItDone()) {
                task.setAsUndone();
                output += ui.unMarkTask(task);
            } else {
                ui.printLine();
                throw new WrongMarkException("This task is not done yet.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printLine();
            output += "OOPS!!! Must choose something to unmark.";
            System.out.println("OOPS!!! Must choose something to unmark.");
        } catch (NullPointerException e) {
            ui.printLine();
            output += "OOPS!!! You chose air.";
            System.out.println("OOPS!!! You chose air.");
        }
        return output;
    }
}
