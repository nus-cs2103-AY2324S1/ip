package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import exceptions.DukeException;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(String fullCommand) {
        super(false);
        String[] parts = fullCommand.split(" "); {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            this.index = taskIndex;
        }
    }
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            taskList.markTask(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("This task does not exist! Try again!");
        }
        storage.writeListToFile(taskList);
        ui.showMarkTaskMessage(taskList.getTaskInString(index), true);
        }
    }


