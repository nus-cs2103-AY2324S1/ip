package ally.commands;

import ally.Storage;
import ally.Ui;
import ally.exceptions.AllyException;
import ally.tasks.AllyList;
import ally.tasks.Deadline;

/**
 * AddDeadlineCommand inherits from Commands.
 */
public class AddDeadlineCommand extends Commands {
    private final String description;
    private final String by;

    /**
     * Constructor for AddDeadlineCommand.
     *
     * @param description
     * @param by
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Adds the new deadline task to AllyList and saves the task.
     * Prints the deadline task.
     *
     * @param allyList
     * @param ui
     * @param storage
     * @throws AllyException
     */
    @Override
    public String run(AllyList allyList, Ui ui, Storage storage) throws AllyException {
        try {
            assert ui != null;
            assert storage != null;
            Deadline ddline = new Deadline(description, by);
            allyList.addElements(ddline);
            storage.appendToFile(ddline);
            return allyList.printNewList(ddline);
        } catch (AllyException e) {
            return e.getMessage();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
