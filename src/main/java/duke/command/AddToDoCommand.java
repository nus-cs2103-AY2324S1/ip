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
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ToDo todo = new ToDo(description);
        taskList.addToDo(todo);
        storage.writeData(taskList.toWriteString());
        System.out.println("Ok. Your tasklist has grown longer with this addition:\n"
                + todo.toString()
                + "\nYou now have " + taskList.getLength() + " things to do.\n");
    }
}
