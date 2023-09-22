package atlas.commands;

import atlas.components.Storage;
import atlas.components.TaskList;

/**
 * Command used to display the list of possible commands.
 */
public class HelpCommand extends Command {
    private static final String helpMsg = "The Olympians will not hear your prayers, mortal. "
            + "These are the abilities available to you:\n\n"
            + "todo - Create Todos\n\n"
            + "deadline - Create Deadlines\n\n"
            + "event - Create Events\n\n"
            + "list - View all your labours\n\n"
            + "date - View all your labours on a specific date\n\n"
            + "remind - View all your reminders of labours due soon\n\n"
            + "mark - Mark a labour as completed\n\n"
            + "unmark - Mark a labour as uncompleted\n";

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return helpMsg;
    }
}
