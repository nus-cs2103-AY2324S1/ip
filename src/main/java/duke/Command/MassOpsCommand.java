package duke.Command;

import java.io.IOException;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;


/**
 * Class that handles the MassOps command.
 */
public class MassOpsCommand extends Command {
    private String input;

    public MassOpsCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws IOException, DukeException {
        String[] inputIndices = input.substring(11).split(" ");
        int[] indices = new int[inputIndices.length];
        for (int i = 0; i < inputIndices.length; i++) {
            indices[i] = Integer.parseInt(inputIndices[i]);
        }
        for (int i = 0; i < inputIndices.length; i++) {
            if ((indices[i] == 0) || (indices[i] > tasks.getSize())) {
                throw new DukeException("These numbers are out of bounds!");
            }
        }
        tasks.deleteManyTasks(indices);
        storage.writeTasksToFile(tasks);
        return ui.printDeleteManyTasks();
    }
}
