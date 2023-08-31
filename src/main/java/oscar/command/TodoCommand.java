package oscar.command;

import oscar.essential.Storage;
import oscar.essential.TaskList;

import oscar.exception.OscarException;

import oscar.task.Task;
import oscar.task.Todo;

/**
 * Command to create a new todo task.
 */
public class TodoCommand extends Command {
    private final String details;

    /**
     * Instantiates a todo command.
     *
     * @param details Description of todo task.
     */
    public TodoCommand(String details) {
        super(false);
        this.details = details;
    }

    /**
     * Creates a new todo task and save it to the task list.
     *
     * @param tasks ArrayList of tasks.
     * @param storage File loading and saving handler.
     * @throws OscarException Incorrect format of todo command.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws OscarException {
        validate();
        Task newTodo = new Todo(details);
        tasks.add(newTodo);
        storage.save(tasks);
        System.out.println("Oscar has added:\n" + newTodo + "\n");
        tasks.listCount();
    }

    /**
     * Validates description of todo task.
     * Format: todo [task].
     *
     * @throws OscarException Description is missing or not within 200 characters.
     */
    public void validate() throws OscarException {
        if (details.isEmpty()) {
            throw new OscarException("Sorry! The description of a todo task cannot be empty.\n");
        } else if (details.length() > 200) {
            throw new OscarException("Sorry! The description of a todo task cannot exceed 200 characters.\n");
        }
    }
}
