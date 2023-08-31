package rua.command;

import java.time.LocalDate;
import rua.task.TaskList;
import rua.common.*;

public class DateSearchCommand implements Command{
    private final LocalDate date;

    public DateSearchCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * {@inheritDoc}
     *
     * @return The exit status after this execution.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints the tasks that happens on the given date and return the current TaskList.
     *
     * @param tasks Current TaskList.
     * @param ui A UI to show messages to the user.
     * @param storage A Storage to save and load tasks
     * @return Current TaskList.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception{
        ui.showMessage(" These are events happening on that day\n");
        String result = tasks.dateSearch(date);
        ui.showMessage(result);
        return tasks;
    }

    /**
     * {@inheritDoc}
     *
     * @param o Another object to be compared with.
     * @return A boolean indicating whether they are equal or not.
     */
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
