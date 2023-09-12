package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

public class FindCommand implements Command {

    @Override
    public String execute(String text, UI ui, TaskList list, Storage storage) throws DukeException {
        String search = text.substring(5);

        if (search.isEmpty()) {
            throw new DukeException("I apologise, sir. " +
                    "But the description of todo cannot be empty");

        } else if (list.size() == 0) {
            throw new DukeException("I apologise, sir." +
                    "But your list is empty.");
        } else {
            boolean hasSearch = false;
            boolean isFirst = true;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).toString().contains(search)) {
                    hasSearch = true;
                    if (hasSearch == true && isFirst == true) {

                        ui.buildMessage("Here are the matching tasks in your list: \n");
                        ui.buildMessage(String.format("\t [%s] [%s] %s \n", list.get(i).getTag(),
                                list.get(i).getStatusIcon(), list.get(i).toString()));
                        isFirst = false;
                    } else if (hasSearch == true && isFirst == false) {
                        ui.buildMessage(String.format("\t [%s] [%s] %s \n", list.get(i).getTag(),
                                list.get(i).getStatusIcon(), list.get(i).toString()));
                    }
                }
            }
            if (hasSearch == false) {
                throw new DukeException("I apologise sir." +
                        "But " + search + " cannot be found in your list.");
            }
            return ui.sendMessage();
        }
    }
}
