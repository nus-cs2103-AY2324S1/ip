package command;

import java.time.LocalDate;

import helper.Storage;
import helper.Ui;
import task.TaskList;

public class DueCommand extends Command {
    public static final String COMMAND_WORD = "due";
    private LocalDate dueDate;

    public DueCommand(int index, LocalDate dueDate) {
        super(index);
        this.dueDate = dueDate;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        String message = list.dueOn(dueDate);
        ui.print(message);
    }
}
