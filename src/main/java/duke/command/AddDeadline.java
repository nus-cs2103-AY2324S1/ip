package duke.command;

import java.time.DateTimeException;
import java.time.LocalDate;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.ui.UI;



public class AddDeadline extends Command {
    public AddDeadline(String s) {
        super(s);
    }

    @SuppressWarnings("checkstyle:WhitespaceAround")
    @Override
    public void execute(TaskList lst, UI io, Storage storage) throws DukeException {
        if (s.isEmpty() || s.equals(" ")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (!s.matches(" \\S.*\\s/by\\s\\d.*")) {
            throw new DukeException(
                    "☹ OOPS!!! Please follow the following pattern to add a task:\n  "
                            + "deadline <task name> /by <deadline>\n");
        } else {
            String[] temp = s.split(" /by ");
            try {
                LocalDate d = LocalDate.parse(temp[1]);
                Task newTask = lst.addTask(temp[0].substring(1), d);
                io.addTask(newTask, lst);
                storage.addToFile(newTask);
            } catch (DateTimeException e) {
                throw new DukeException(
                        "☹ OOPS!!! Please follow the following pattern to input the time:\n  "
                                + "deadline <task name> /by <yyyy-mm-dd>\n");
            }
        }
    }
}
