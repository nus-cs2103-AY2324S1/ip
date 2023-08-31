package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import exceptions.DukeException;

public class DeleteCommand extends Command {
    private int taskToDeleteIndex;

    public DeleteCommand(String fullCommand) {
        super(false);
        String[] parts = fullCommand.split(" ");
        this.taskToDeleteIndex = Integer.parseInt(parts[1]) - 1;
    }

    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            taskList.deleteTask(this.taskToDeleteIndex);
        } catch (IndexOutOfBoundsException e) {
        throw new DukeException("This task does not exist! Try again!");
    }
        storage.writeListToFile(taskList);
        taskList.printTaskListInString();
    }
}
