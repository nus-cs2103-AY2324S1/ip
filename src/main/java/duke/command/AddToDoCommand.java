package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.ToDo;

public class AddToDoCommand extends Command {
    String description;
    public AddToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ToDo todo = new ToDo(description);
        tasks.addToDo(todo);
        storage.writeData(tasks.toWriteString());
        String returnMessage = "Ok. Your tasklist has grown longer with this addition:\n"
                + todo.toString()
                + "\nYou now have " + tasks.getLength() + " things to do."
                + "\nI'm sure you're dying to finish them.";
        return returnMessage;
    }
}
