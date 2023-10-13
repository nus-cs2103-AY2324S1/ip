package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.exception.WrongMarkException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to mark a task as done
 */
public class MarkCommand extends Command {
    private int taskNumber;

    /**
     * Represents a constructor of the MarkCommand object
     */
    public MarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the MarkCommand and returns a string
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
        } catch (IndexOutOfBoundsException e) {
            ui.printLine();
            output += "PIKA PIKA!!! Must choose something to mark.";
            System.out.println("PIKA PIKA!!! Must choose something to mark.");
        } catch (NullPointerException e) {
            ui.printLine();
            output += "PIKA PIKA!!! You chose air.";
            System.out.println("PIKA PIKA!!! You chose air.");
        }
        return output;
    }
}
