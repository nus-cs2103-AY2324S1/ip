package duke.commands;

import duke.exceptions.NoIndexException;
import duke.tools.Storage;
import duke.tasks.Task;
import duke.tools.TaskList;
import duke.tools.Ui;

public class DeleteCommand extends Command {

    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoIndexException {
        String desc = fullCommand.replaceAll("[^0-9]", "");
        if (desc.equals("")) {
            throw new NoIndexException("No Index");
        }
        int index = Integer.parseInt(desc);

        if (tasks.size() < index || index < 1) {
            throw new NoIndexException(Integer.toString(index));
        }
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        ui.showDeleteMessage(task, tasks.size());
    }
}
