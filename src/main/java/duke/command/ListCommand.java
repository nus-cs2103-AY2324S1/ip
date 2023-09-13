package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import duke.exception.InvalidDatetimeFormatException;
import duke.helper.DatetimeHelper;
import duke.io.FileIO;
import duke.io.Printer;
import duke.task.Task;
import duke.task.TaskList;

/** Represents a list command. Has an optional /before argument to return tasks before a date. */
public class ListCommand extends Command {
    private String before;

    /**
     * Returns a ListCommand
     *
     * @param out Printer to print output to
     * @param tasklist Tasklist to read
     * @param file File to save tasks to
     * @param before the optional date argument to filter tasks before date
     * @return a ListCommand
     */
    public ListCommand(Printer out, TaskList taskList, FileIO file, String before) {
        super(out, taskList, file);
        this.before = before;
    }

    /**
     * The actions to take during the command
     *
     * @throws DateTimeParseException when before is not "" or a valid datetime
     */
    @Override
    public void action() {
        Stream<Task> tasks = taskList.stream();

        if (!before.equals("")) {
            try {
                LocalDateTime beforeTime = DatetimeHelper.parse(before);
                tasks = tasks.filter(Task.filterByBeforeDatetime(beforeTime));
            } catch (DateTimeParseException e) {
                throw new InvalidDatetimeFormatException("before", "list");
            }
        }

        out.print(tasks);
    }
}
