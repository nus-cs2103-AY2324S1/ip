package duke.command;

import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Represents a command to add tasks to the task list.
 */
public class AddCommand extends Command {
    private String[] words;
    private String category;
    private boolean isExit = false;

    /**
     * Constructor for creating an AddCommand.
     *
     * @param words The array of words containing user input.
     * @param category The category of the task to be added ("T" for Todo, "D" for Deadline, "E" for Event).
     */
    public AddCommand(String[] words, String category) {
        this.words = words;
        this.category = category;
    }

    /**
     * Checks if the AddCommand should trigger the program to exit.
     *
     * @return false since it is not an exit command.
     */
    @Override
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the AddCommand by adding the specified task to the task list.
     *
     * @param tasks   The list of tasks to which the task will be added.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for updating task data.
     * @throws DukeException If there is an issue executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            switch (category) {
            case "T":
                this.addTodo(tasks, ui, storage);
                break;
            case "D":
                this.addDeadline(tasks, ui, storage);
                break;
            case "E":
                this.addEvent(tasks, ui, storage);
                break;
            default:
                assert false : "There may be wrong parameters inputted in, "
                        + "please check again!";
            }
        } catch (DateTimeParseException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Adds a todo task to the task list.
     *
     * @param tasks   The list of tasks to which the task will be added.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for updating task data.
     * @throws DukeException If there is an issue adding the task.
     */
    private void addTodo(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (words.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        //creating the to do
        Todo todo = new Todo(words[1]);

        //adding to taskArr and updates the storage
        tasks.addTask(todo);
        storage.updateFile(tasks);

        //printing messages
        String message = "Got it. I've added this task: \n" + todo
                + "\nNow you have " + tasks.size() + " tasks in the list.";

        ui.updateMessage(message);
        ui.updateRecentCommand("add");
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param tasks   The list of tasks to which the task will be added.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for updating task data.
     * @throws DukeException If there is an issue adding the task.
     */
    private void addDeadline(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (words.length < 2) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }

        String text = words[1];
        String[] split = text.split(" /by ", 2);
        String desc = split[0];
        String by = split[1];
        Deadline dl = new Deadline(desc, by);

        //add task to taskArr and updating the storage
        tasks.addTask(dl);
        storage.updateFile(tasks);

        //printing the messages
        String dlMessage = "Got it. I've added this task: \n" + dl
                + "\nNow you have " + tasks.size() + " tasks in the list.";
        ui.updateMessage(dlMessage);
        ui.updateRecentCommand("add");
    }

    /**
     * Adds an event task to the task list.
     *
     * @param tasks   The list of tasks to which the task will be added.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for updating task data.
     * @throws DukeException If there is an issue adding the task.
     */
    private void addEvent(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (words.length < 2) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        }

        String text2 = words[1];
        String[] slice = text2.split(" /from ", 2);
        String descr = slice[0].trim();
        String[] slicess = slice[1].split(" /to ", 2);
        String from = slicess[0].trim();
        String to = slicess[1].trim();

        Event e = new Event(descr, from, to);

        //add task to taskArr and updating storage
        tasks.addTask(e);
        storage.updateFile(tasks);

        //print messages
        String eMessage = "Got it. I've added this task: \n" + e
                + "\nNow you have " + tasks.size() + " tasks in the list. ";
        ui.updateMessage(eMessage);
        ui.updateRecentCommand("add");
    }
}
