package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeBadInputException;
import duke.task.Task;

/**
 * Represents the mark/unmark command
 */
public class MarkCommand extends Command {

    /**
     * true if mark command else unmark
     */
    private boolean isMark;
    /**
     * index of the task being marked/unmarked
     */
    private int index;

    /**
     * Constructor for the duke.command.MarkCommand class
     *
     * @param mark  - true if mark command else unmark
     * @param index - index of the task in question
     */
    public MarkCommand(boolean mark, int index) {
        this.isMark = mark;
        this.index = index - 1;
    }

    /**
     * Method to encapsulate the execution logic of the command
     *
     * @param taskList - the task list instance of the current duke
     * @param ui       - the ui instance of DUKE
     * @param storage  - the storage instance to allow the command to write to the
     *                 storage
     * @throws DukeBadInputException - if the input cannot be used
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeBadInputException {
        Command.validateIndex(this.index, taskList.length());
        Task task = taskList.get(index);

        // only toggle if mark != completed as if they are the same then there is no
        // effect
        String resp;
        if (isMark != task.isComplete()) {
            task.toggleComplete();
            resp = isMark ? "Quack! Congrats for finishing the task!"
                    : "Quack, I've marked this task as not done yet :(";
        } else {
            resp = isMark ? "Quack! This task is already done QUACK!"
                    : "Quack! you cant unmark something that isn't done yet!!";
        }

        // writes to UI
        ui.println(resp);
        ui.println(task.toString());
        try {
            if (!storage.rewriteAll(taskList.getAllTask())) {
                ui.unexpectedError("not all tasks were successfully written, please contact my mother :( ");
            }
        } catch (IOException e) {
            ui.unexpectedError("error when writing to storage: " + e.getMessage());
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
     * @param other the other command in question
     * @return true if there are equals
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof MarkCommand) {
            MarkCommand command = (MarkCommand) other;
            return this.index == command.index && this.isMark == command.isMark;
        }
        return false;
    }
}
