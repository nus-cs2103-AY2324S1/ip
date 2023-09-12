package duke.command;

import duke.*;

import java.io.IOException;

public class TodoCommand implements Command {

    @Override
    public String execute(String text, UI ui, TaskList list, Storage storage) throws DukeException {
        String description = text.substring(4);
        if (description.isEmpty()) {
            throw new DukeException("I apologise, sir. " +
                    "But the description of todo cannot be empty");
        } else {
            try {
                storage.appendToFile(text + "\n");
            } catch (IOException e) {
                ui.buildMessage("Something went wrong: " + e.getMessage() + " \n");
                return ui.sendMessage();
            }

            Todo todo = new Todo(description.trim());
            list.add(todo);

            ui.buildMessage("Noted Sir. I've added this task to your list: \n");
            ui.buildMessage(String.format("\t [%s] [%s] %s \n", todo.getTag(),
                    todo.getStatusIcon(), todo.toString()));
            ui.buildMessage(String.format("As of now, you have %d tasks on the agenda. \n",
                    list.size()));
            return ui.sendMessage();

        }
    }
}
