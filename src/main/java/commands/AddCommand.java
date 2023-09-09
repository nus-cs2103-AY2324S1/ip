package commands;

import functional.DukeException;
import functional.TaskList;
import functional.Ui;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.util.Arrays;



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
     * @param tasks  The given taskList
     * @param ui the UI object containing the user input
     * @param marked whether the task is marked
     * @param load whether the task is loading from drive memory
     */
    public void execute(TaskList<Task> tasks, Ui ui, boolean marked, boolean load) throws DukeException {
        if ((Arrays.asList("todo", "deadline", "event").contains(ui.get(0)))
                && ui.length() == 1) {
            System.out.println(ui.showLine() + "\n"
                    + "☹ OOPS!!! The description of a todo cannot be empty.\n"
                    + ui.showLine());
            throw new DukeException();
        }
        switch (ui.get(0)) {
        case "todo":
            Task job = new ToDo(ui.getInput(), marked);
            tasks.add(job);
            if (!load) {
                System.out.println(job.addTask(tasks.size()));
            }
            break;
        case "deadline":
            try {
                job = new Deadline(ui.getInput(), marked);
                tasks.add(job);
                if (!load) {
                    System.out.println(job.addTask(tasks.size()));
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(ui.showLine() + "\n"
                        + "☹ OOPS!!! I'm sorry, the format entered was incorrect :-(\n"
                        + ui.showLine());
                throw new DukeException();
            }
            break;
        case "event":
            try {
                job = new Event(ui.getInput(), marked);
                tasks.add(job);
                if (!load) {
                    System.out.println(job.addTask(tasks.size()));
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(ui.showLine() + "\n"
                        + "☹ OOPS!!! I'm sorry, the format entered was incorrect :-(\n"
                        + ui.showLine());
                throw new DukeException();
            }
            break;
        default:
            System.out.println(ui.showLine() + "\n"
                    + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + ui.showLine());
            throw new DukeException();
        }
    }
}
