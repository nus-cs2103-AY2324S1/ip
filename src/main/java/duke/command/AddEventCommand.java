package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Event;

import java.time.LocalDateTime;

public class AddEventCommand extends Command {
    String description;
    LocalDateTime from;
    LocalDateTime to;
    public AddEventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event event = new Event(description, from, to);
        taskList.addEvent(event);
        storage.writeData(taskList.toWriteString());
        System.out.println("Ok. Your tasklist has grown longer with this addition:\n"
                + event.toString()
                + "\nYou now have " + taskList.getLength() + " things to do.\n");
    }
}
