package Ally.Commands;

import Ally.*;
import Ally.Exceptions.AllyException;
import Ally.Tasks.AllyList;
import Ally.Tasks.Deadline;

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
    public void run(AllyList allyList, Ui ui, Storage storage) throws AllyException {
        try {
            Deadline ddline = new Deadline(description, by);
            allyList.addElements(ddline);
            storage.appendToFile(ddline);
            allyList.printNewList(ddline);
        } catch (AllyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
