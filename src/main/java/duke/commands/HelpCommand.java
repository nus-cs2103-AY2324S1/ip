package duke.commands;

/**
 * A command to print all the commands available in the chatBot.
 */
public class HelpCommand extends Command {

    /**
     * The command that will display the helper lines.
     */
    @Override
    public String execute() {
        String commands = (
                        "These are the commands I can take in \n\n"
                        + "To record tasks type \"todo TASKNAME\" \n\n"
                        + "To record tasks with deadlines, type \"deadline TASKNAME /by DD/MM/YYYY HHmm\" \n\n"
                        + "To record events, type \"events TASKNAME /from DD/MM/YYYY HHmm /to DD/MM/YYYY HHmm\" , \n\n"
                        + "To view your list of events, type list. \n\n"
                        + "To mark your events as done or undone, type \"mark INDEX\" or \"unmark INDEX\"\n\n"
                        + "To delete tasks from the list, type \"delete INDEX\" \n\n"
                        + "To search for tasks with a keyword, type \"find KEYWORD\"\n\n"
                        + "Lastly, to exit the chatBot, type \"bye\" \n"
        );
        System.out.println(commands);
        return commands;
    }
}
