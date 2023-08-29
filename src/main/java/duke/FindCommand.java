package duke;

import duke.command.Command;
import duke.task.TaskList;

public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword keyword to be searched for in the description.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Prints the list of tasks that contains the keyword.
     *
     * @param taskList list of tasks that contains the keyword.
     * @param ui       ui object to print the list of tasks.
     * @param storage  storage object to save the list of tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printResultList(taskList.findTask(this.keyword));
    }
}
