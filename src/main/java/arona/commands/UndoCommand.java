package arona.commands;

import java.util.Stack;

import arona.storage.Storage;
import arona.task.TaskList;
import arona.ui.Ui;

/**
 * Represents a command to undo the previous action in the Arona application.
 * When executed, this command reverses the effects of the most recent user action
 * by examining the command history and applying the appropriate undo action.
 */
public class UndoCommand extends Command {
    private Storage storage;
    private Stack<Command> commandHistory;

    /**
     * Initializes a new instance of the UndoCommand class with the specified
     * task list, user interface, storage, and command history.
     *
     * @param taskList       The task list for managing tasks.
     * @param ui             The user interface for displaying messages.
     * @param storage        The storage for saving and loading tasks.
     * @param commandHistory The stack of previously executed commands to track user actions.
     */
    public UndoCommand(TaskList taskList, Ui ui, Storage storage, Stack<Command> commandHistory) {
        super(taskList, ui);
        this.storage = storage;
        this.commandHistory = commandHistory;
    }

    /**
     * Executes the "Undo" command, which reverses the most recent user action
     * by examining the command history and applying the appropriate undo action.
     *
     * @return A string message indicating the result of the undo operation.
     */
    @Override
    public String execute() {
        return undoCommand(commandHistory);
    }

    /**
     * Performs the undo operation by analyzing the command history and applying
     * the appropriate undo action for the most recent command.
     *
     * @param commandHistory The stack of previously executed commands.
     * @return A string message indicating the result of the undo operation.
     */
    public String undoCommand(Stack<Command> commandHistory) {
        while (!commandHistory.empty()) {
            Command lastCommand = commandHistory.pop();

            if (!(lastCommand instanceof UndoableCommand)) {
                continue;
            }

            if (lastCommand instanceof MarkCommand || lastCommand instanceof UnmarkCommand) {
                int taskIndex = ((UndoableCommand) lastCommand).getTaskIndex();

                if (taskIndex < 0 || taskIndex >= taskList.getTasks().size()) {
                    continue;
                }
                return ((UndoableCommand) lastCommand).undo();
            } else if (lastCommand instanceof DeleteCommand) {
                int taskIndex = ((UndoableCommand) lastCommand).getTaskIndex();

                if (taskIndex < 0) {
                    continue;
                }
                return ((UndoableCommand) lastCommand).undo();
            } else {
                return ((UndoableCommand) lastCommand).undo();
            }
        }
        return ui.showCannotUndo();
    }
}
