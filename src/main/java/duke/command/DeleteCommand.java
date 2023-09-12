package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

import java.io.IOException;

public class DeleteCommand implements Command{

    @Override
    public String execute(String text, UI ui, TaskList list, Storage storage) throws DukeException {
        String[] splitText = text.split(" ");

        try {
            int numToDelete = Integer.parseInt(splitText[1]) - 1;
            if (numToDelete > list.size() - 1) {
                throw new DukeException("I apologise, sir. This task does not exist");
            }

            storage.appendToFile(text + "\n");

            ui.buildMessage("Alright Sir, I have removed this task from the list for you.\n");
            ui.buildMessage(String.format("\t [%s] [%s] %s \n", list.get(numToDelete).getTag(),
                    list.get(numToDelete).getStatusIcon(), list.get(numToDelete).toString()));

            list.remove(numToDelete);
            ui.buildMessage(String.format("Now you have %d tasks left. \n", list.size()));

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
