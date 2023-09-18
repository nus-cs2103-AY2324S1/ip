package oscar.command;

import oscar.essential.ItemList;
import oscar.essential.Storage;
import oscar.exception.OscarException;
import oscar.item.Task;
import oscar.item.TodoTask;

/**
 * Command to create a new todo task.
 */
public class TodoCommand extends Command {
    private final String details;

    /**
     * Instantiates a todo command.
     *
     * @param d Description of todo task.
     */
    public TodoCommand(String d) {
        this.details = d;
    }

    /**
     * Creates a new todo task and save it to the info list.
     *
     * @param infos   ArrayList of infos.
     * @param storage File loading and saving handler.
     * @return String output of todo task.
     * @throws OscarException Incorrect format of todo command.
     */
    @Override
    public String execute(ItemList infos, Storage storage) throws OscarException {
        assert infos != null;
        assert storage != null;
        validate();
        Task newTodo = new TodoTask(details);
        infos.add(newTodo);
        storage.save(infos);
        infos.listCount();
        return "Oscar has added:\n" + newTodo + "\n";
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
