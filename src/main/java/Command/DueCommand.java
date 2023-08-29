package Command;

import java.time.LocalDate;

import Exception.*;
import Helper.*;
import Task.*;

public class DueCommand extends Command {
    public static final String COMMAND_WORD = "due";
    LocalDate dueDate;

    public DueCommand(int index, LocalDate dueDate) {
        super(index);
        this.dueDate = dueDate;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        String message = list.dueOn(dueDate);
        ui.print(message);
    }
}
