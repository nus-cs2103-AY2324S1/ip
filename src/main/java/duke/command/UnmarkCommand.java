package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

import java.io.IOException;

public class UnmarkCommand implements Command {

    @Override
    public String execute(String text, UI ui, TaskList list) throws DukeException {
        try {
            String[] splitText = text.split(" ");
            int numToUnmark = Integer.parseInt(splitText[1]) - 1;
            if (numToUnmark > list.size() - 1) {
                throw new DukeException("I apologise, sir. This task does not exist");
            }
            list.get(numToUnmark).markAsIncomplete();

            storage.appendToFile(text + "\n");

            ui.buildMessage("Alright! I'll uncheck this task for you: \n");
            ui.buildMessage(String.format("\t [%s] [%s] %s", list.get(numToUnmark).getTag(),
                    list.get(numToUnmark).getStatusIcon(), list.get(numToUnmark)));
            return ui.sendMessage();
        } catch (NumberFormatException e) {
            throw new DukeException("I apologise, sir. But you have to key in a task number.");
        } catch (
                IOException e) {
            ui.buildMessage("Something went wrong: " + e.getMessage() + "\n");
            return ui.sendMessage();
        }
    }
}
