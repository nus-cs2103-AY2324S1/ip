package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeBadInputException;
import duke.task.Task;

/**
 * Represents the delete command
 */
public class DeleteCommand extends Command {

    /**
     * the index of the task being deleted
     */
    private int index;

    /**
     * Constructor for the duke.command.MarkCommand class
     *
     * @param index - the index of the task being deleted
     */
    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Method to encapsulate the execution logic of the command
     *
     * @param taskList - the task list instance  of the current duke
     * @param ui       - the ui instance of DUKE
     * @param storage  - the storage instance to allow the command to write to the storage
     * @throws DukeBadInputException - if the input cannot be used
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeBadInputException {
        Command.validateIndex(this.index, taskList.length());

        // Check if able to remove
        Task removed = taskList.remove(index);
        try {
            if (!storage.rewriteAll(taskList.getAllTask())) {
                ui.unexpectedError("not all tasks were successfully written, please contact my mother :( ");
            }
        } catch (IOException e) {
            ui.unexpectedError("error when writing to storage: " + e.getMessage());
        }

        // print to screen if able to remove
        ui.println("Quack! I have removed this task:");
        ui.println(removed.toString());
        ui.println("Quack! Quack is currently remembering " + taskList.length() + " tasks.");
    }

    /**
     * Checks if the command is the exit command
     *
     * @return true if it is the exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Checks if it is the exact same command
     * @param other the other command in question
     * @return true if there are equals
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof DeleteCommand) {
            DeleteCommand command = (DeleteCommand) other;
            return this.index == command.index;
        }
        return false;
    }
}
