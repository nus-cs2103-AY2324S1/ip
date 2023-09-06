package commands;

import functional.DukeException;
import functional.TaskList;
import functional.Ui;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.util.Arrays;

public class AddCommand extends Command {

    public AddCommand() {
        super();
    }

    public void execute(TaskList<Task> tasks, Ui ui, boolean marked, boolean load) throws DukeException {
        if ((Arrays.asList("todo", "deadline", "event").contains(ui.get(0)))
                && ui.length() == 1) {
            System.out.println(ui.showLine() + "\n" +
                    "☹ OOPS!!! The description of a todo cannot be empty.\n"
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
                System.out.println(ui.showLine() + "\n" +
                        "☹ OOPS!!! I'm sorry, the format entered was incorrect :-(\n"
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
                System.out.println(ui.showLine() + "\n" +
                        "☹ OOPS!!! I'm sorry, the format entered was incorrect :-(\n"
                        + ui.showLine());
                throw new DukeException();
            }
            break;
        default:
            System.out.println(ui.showLine() + "\n" +
                    "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + ui.showLine());
            throw new DukeException();
        }
    }
}
