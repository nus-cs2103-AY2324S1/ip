package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.exception.WrongMarkException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represent a command to mark a task as done
 */
public class MarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructor of the MarkCommand object
     */
    public MarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Execute the MarkCommand and returns a string
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws WrongMarkException {
        String output = "";
        try {
            Task task = tasks.get(taskNumber - 1);
            if (!task.isItDone()) {
                task.setAsDone();
                output += ui.markTask(task);
            } else {
                ui.printLine();
                throw new WrongMarkException("This task is already done.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printLine();
            output += "OOPS!!! Must choose something to mark.";
            System.out.println("OOPS!!! Must choose something to mark.");
        } catch (NullPointerException e) {
            ui.printLine();
            output += "OOPS!!! You chose air.";
            System.out.println("OOPS!!! You chose air.");
        }
        return output;
    }
}
