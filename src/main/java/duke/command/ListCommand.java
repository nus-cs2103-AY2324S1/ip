package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * Executes the command to show all Tasks currently in the TaskList.
 */
public class ListCommand implements Command {

    /**
     * @inheritDoc
     */
    @Override
    public String execute(String text, UI ui, TaskList list, Storage storage) throws DukeException {
        if (list.size() == 0) {
            throw new DukeException("I apologise, sir. But you have no tasks on your list.");
        }
        for (int i = 0; i < list.size(); i++) {

            boolean isNull = list.get(i) == null;

            if (isNull) {
                break;
            } ui.buildMessage(String.format("%d. [%s] [%s] %s \n", i + 1, list.get(i).getTag(),
                            list.get(i).getStatusIcon(), list.get(i)));
        } return ui.sendMessage();

    }
}
