package banterbot.command;

import java.time.LocalDate;

import banterbot.exception.DukeException;
import banterbot.exception.InvalidCommandException;
import banterbot.helper.Storage;
import banterbot.helper.Ui;
import banterbot.task.Deadline;
import banterbot.task.Event;
import banterbot.task.Task;
import banterbot.task.TaskList;
import banterbot.task.ToDo;

/**
 * Represents a Command that specifically adds a Task to the TaskList.
 */
public class AddCommand extends Command {
    /** Task to be parsed into Command. */
    private String task;

    /**
     * Constructs an AddCommand with a BanterBot.task and an index.
     * @param index
     * @param task
     */
    public AddCommand(int index, String task) {
        super(index);
        this.task = task;
    }

    /**
     * Takes in the input given by the User and wraps the BanterBot.task details in
     * a Task Object.
     * @param input
     * @return a Task
     * @throws InvalidCommandException
     */
    private Task parseTask(String input) throws InvalidCommandException {
        try {
            // Task is todo
            if (input.startsWith("todo")) {
                String description = input.split("todo", 2)[1].strip();
                ToDo todo = new ToDo(description);

                return todo;
            }

            // Task is deadline
            if (input.startsWith("deadline")) {
                String[] parts = input.split("deadline", 2);
                String description = parts[1].split("/by", 2)[0].strip();
                String by = parts[1].split("/by", 2)[1].strip();
                LocalDate date = LocalDate.parse(by);
                Deadline deadline = new Deadline(description, date);

                return deadline;
            }

            // Task is event
            String[] parts = input.split("event", 2);
            String description = parts[1].split("/from", 2)[0].strip();
            String time = parts[1].split("/from", 2)[1];
            String from = time.split("/to", 2)[0].strip();
            String to = time.split("/to", 2)[1].strip();
            Event event = new Event(description, from, to);

            return event;
        } catch (Exception e) {
            throw new InvalidCommandException("Wrong way to use the BanterBot.command bruv!");
        }

    }

    /**
     * Add a Task to the TaskList and prints a message through the UI.
     * @param list
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        assert !list.equals(null) : "list has been initialised";
        String message = list.add(parseTask(this.task));
        return message;
    }
}
