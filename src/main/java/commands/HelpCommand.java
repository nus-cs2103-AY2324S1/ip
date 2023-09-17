package commands;

import storage.DataFile;
import tasks.TaskList;

public class HelpCommand extends Command {

    @Override
    public String execute(TaskList tasks, DataFile dF) {
        return getHelpFromAllCommands();
    }

    public static String help() {
        return "Help command is used to get all the command usages.\n"
                + "Eg. of Usage:\n" + "help\n";
    }

    private String getHelpFromAllCommands() {
        return DeadlineCommand.help() + "\n"
                + DeleteCommand.help() + "\n"
                + EventCommand.help() + "\n"
                + FindCommand.help() + "\n"
                + ListCommand.help() + "\n"
                + MarkCommand.help() + "\n"
                + TodoCommand.help() + "\n"
                + TodoTimeCommand.help() + "\n"
                + UnmarkComment.help() + "\n"
                + HelpCommand.help() + "\n";
    }
}
