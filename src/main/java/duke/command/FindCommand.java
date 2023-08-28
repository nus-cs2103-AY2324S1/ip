package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The FindCommand is for "find" command.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * The constructor for a FindCommand.
     *
     * @param keyword The string representation of the keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * This method is used to execute the find command.
     *
     * @param tasks The TaskList to be worked on.
     * @param ui The Ui to be worked on.
     * @param storage The Storage to be worked on.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int newIndex = 0;
        System.out.println("\tHere are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(keyword)) {
                System.out.printf("\t\t%d. %s\n", newIndex + 1, tasks.get(i));
                newIndex++;
            }
        }
        ui.printLine();
    }

    /**
     * This method is used to check whether it is an exit command.
     *
     * @return Returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
