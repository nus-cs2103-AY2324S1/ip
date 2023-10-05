package commands;

import java.util.Arrays;

import functional.DukeException;
import functional.TaskList;
import functional.Ui;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * Command to add a task
 */
public class AddCommand extends Command {

    /**
     * Constructs the AddCommand object
     */
    public AddCommand() {
        super();
    }

    /**
     * Constructs an `AddCommand` object with the given taskList, specified user input,
     * marked status and boolean indicating whether it is loading from drive memory
     *
     * @param tasks  The given taskList
     * @param ui     the UI object containing the user input
     * @param status whether the task is marked and whether the task is loading from drive memory
     */
    public String execute(TaskList<Task> tasks, Ui ui, boolean... status) throws DukeException {
        if ((Arrays.asList("todo", "deadline", "event").contains(ui.get(0)))
                && ui.length() == 1) {
            throw new DukeException(ui.showLine() + "\n"
                    + "☹ OOPS!!! The description of a todo cannot be empty.\n"
                    + ui.showLine());
        }
        boolean notLoadingFromMem = status.length > 1 && !status[1];
        switch (ui.get(0)) {
        case "todo":
            Task job = new ToDo(ui.getInput(), status[0]);
            tasks.add(job);
            if (notLoadingFromMem) {
                return job.addTask(tasks.size());
            }
            break;
        case "deadline":
            try {
                job = new Deadline(ui.getInput(), status[0]);
                tasks.add(job);
                if (notLoadingFromMem) {
                    return job.addTask(tasks.size());
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(ui.showLine() + "\n"
                        + "☹ OOPS!!! I'm sorry, the format entered was incorrect :-(\n"
                        + ui.showLine());
            }
            break;
        case "event":
            try {
                job = new Event(ui.getInput(), status[0]);
                tasks.add(job);
                if (notLoadingFromMem) {
                    return job.addTask(tasks.size());
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(ui.showLine() + "\n"
                        + "☹ OOPS!!! I'm sorry, the format entered was incorrect :-(\n"
                        + ui.showLine());
            }
            break;
        default:
            throw new DukeException(ui.showLine() + "\n"
                    + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + ui.showLine());
        }
        return "no output";
    }
}
