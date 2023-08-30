package duke.command;

import java.time.DateTimeException;
import java.time.LocalDate;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.ui.UI;

public class AddEvent extends Command {
    public AddEvent(String s) {
        super(s);
    }

    @Override
    public void execute(TaskList lst, UI io, Storage storage) throws DukeException {
        if (s.isEmpty() || s.equals(" ")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (!s.matches(" \\S.*\\s/from\\s\\d.*\\s/to\\s\\d.*")) {
            throw new DukeException(
                    "☹ OOPS!!! Please follow the following pattern to add a task:\n  "
                            + "deadline <task name> /by <deadline>\n");
        } else {
            String[] temp = s.split(" /");
            try {
                LocalDate dFrom = LocalDate.parse(temp[1].substring(5));
                LocalDate dTo = LocalDate.parse(temp[2].substring(3));
                Task newTask = lst.addTask(temp[0].substring(1), dFrom, dTo);
                io.addTask(newTask, lst);
                storage.addToFile(newTask);
            } catch (DateTimeException e) {
                throw new DukeException(
                        "☹ OOPS!!! Please follow the following pattern to input the time:\n  "
                                + "event <task name> /from <yyyy-mm-dd> /to <yyyy-mm-dd>\n");
            }

        }
    }
}
