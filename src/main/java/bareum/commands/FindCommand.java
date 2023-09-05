package bareum.commands;

import bareum.Storage;
import bareum.TaskList;
import bareum.Ui;

/**
 * This class implements the command for finding and listing all the tasks containing the keyword specified by the user.
 */
public class FindCommand extends Command {
    /**
     * Keyword user is searching for.
     */
    private String keyword;

    /**
     * Create a new instance of a command that finds and lists all the tasks
     * containing the keyword specified by the user.
     * @param keyword Keyword user is searching for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        Ui.reply("Here are the matching tasks in your list!");
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(this.keyword)) {
                System.out.println((i + 1) + ". " + taskList.get(i).toString());
            }
        }
    }
}
