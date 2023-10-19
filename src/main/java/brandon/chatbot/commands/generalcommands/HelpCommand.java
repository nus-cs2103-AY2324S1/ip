package brandon.chatbot.commands.generalcommands;

import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;

/**
 * Represents a command that prints a help message regarding what commands are available to the user.
 */
public class HelpCommand extends Command {
    public static final String HELP_MESSAGE = "here is the list of things I think I can do...ㅜㅅㅜ\n"
            + "   1. todo TASK_TITLE #TAGS\n"
            + "   2. deadline TASK_TITLE /by your_deadline #TAGS\n"
            + "   3. event TASK_TITLE /from starting_time /to end_time #TAGS\n"
            + "   4. find TASK_TITLE #TAGS\n"
            + "   5. list\n"
            + "   6. delete TASK_INDEX\n"
            + "   7. mark TASK_INDEX\n"
            + "   8. unmark TASK_INDEX\n"
            + "   9. help\n"
            + "   10. bye";
    @Override
    public CommandResult execute() throws Exception {
        return new CommandResult(HELP_MESSAGE);
    }
}
