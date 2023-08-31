package rua.command;

import java.time.LocalDate;

import rua.task.TaskList;
import rua.common.*;

public class DateSearchCommand implements Command {
    private final LocalDate date;

    public DateSearchCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        ui.showMessage(" These are events happening on that day\n");
        String result = tasks.searchByDate(date);
        ui.showMessage(result);
        return tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof DateSearchCommand)) {
            return false;
        }

        DateSearchCommand c = (DateSearchCommand) o;
        return c.date.isEqual(this.date);
    }
}
