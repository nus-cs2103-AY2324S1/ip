package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

import java.io.IOException;

public class MarkCommand implements Command {

    @Override
    public String execute(String text, UI ui, TaskList list, Storage storage) throws DukeException {
        try {
            String[] splitText = text.split(" ");
            int numToMark = Integer.parseInt(splitText[1]) - 1;
            if (numToMark > list.size() - 1) {
                throw new DukeException("I apologise, sir. This task does not exist");
            }

            list.get(numToMark).markAsComplete();


            storage.appendToFile(text + "\n");


            ui.buildMessage("Alright! I'll check this task as complete for you: \n");
            ui.buildMessage(String.format("\t [%s] [%s] %s", list.get(numToMark).getTag(),
                    list.get(numToMark).getStatusIcon(), list.get(numToMark)));
            return ui.sendMessage();
        } catch (NumberFormatException e) {
            throw new DukeException("I apologise, sir. But you have to key in a task number.");
        } catch (IOException e) {
            ui.buildMessage("Something went wrong: " + e.getMessage() + "\n");
            return ui.sendMessage();
        }
    }
}
