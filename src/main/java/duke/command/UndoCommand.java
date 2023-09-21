package duke.command;

import duke.main.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A class for the command for undo-ing previous user command.
 */
public class UndoCommand extends Command {

    /**
     * The constructor for the undo command.
     */
    public UndoCommand() {}

    @Override
    public String execute(TaskList taskList, Storage storage, CommandList commandList, boolean write) {
        if (commandList.size() == 0) {
            return "JonBird:\n\tThere are no more commands to undo!";
        }
        Command lastCommand = commandList.getCommand(commandList.size() - 1);
        commandList.removeLastCommand();
        String reply = this.printCommand(taskList);
        if (lastCommand instanceof DeleteCommand) {
            DeleteCommand temp = (DeleteCommand) lastCommand;
            Task task = temp.getTask();
            taskList.insertTask(temp.getIndex() - 1, task);
            reply += "\n\tI have re-added: " + task.printTask();
        } else if (lastCommand instanceof AddCommand) {
            Task task = taskList.getTask(taskList.size() - 1);
            taskList.removeTask(taskList.size() - 1);
            reply += "\n\tI have removed: " + task.printTask();
        } else if (lastCommand instanceof MarkCommand) {
            MarkCommand temp = (MarkCommand) lastCommand;
            Task task = taskList.getTask(temp.getIndex() - 1);
            UnmarkCommand result = new UnmarkCommand(temp.getIndex());
            result.execute(taskList, storage, commandList, false);
            reply += "\n\tI have unmarked: " + task.printTask();
        } else if (lastCommand instanceof UnmarkCommand) {
            UnmarkCommand temp = (UnmarkCommand) lastCommand;
            Task task = taskList.getTask(temp.getIndex() - 1);
            MarkCommand result = new MarkCommand(temp.getIndex());
            result.execute(taskList, storage, commandList, false);
            reply += "\n\tI have marked: " + task.printTask();
        }
        storage.writeData(taskList.convertToFileContent());
        storage.previousCommandsWriter(commandList.convertToFileContent());
        return reply;
    }

    @Override
    public String printCommand(TaskList taskList) {
        return "JonBird:\n\tI have undo your last command!";
    }
}
