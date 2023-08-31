package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeBadInputException;

/**
 * Represents the list command
 */
public class ListCommand extends Command {
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
        if (taskList.length() == 0) {
            ui.println("Quack Quack, you have not entered any tasks yet!");
            ui.println("Create new tasks with the todo, deadline or event command");
            return;
        }
        ui.println("Quack Quack, here are the tasks in quack's memory:");
        for (int i = 0; i < taskList.length(); i++) {
            ui.println((i + 1) + "." + taskList.get(i));
        }
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
     *
     * @param other the other command in question
     * @return true if there are equals
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof ListCommand;
    }
}
