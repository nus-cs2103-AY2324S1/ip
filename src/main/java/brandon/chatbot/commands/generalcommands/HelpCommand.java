package brandon.chatbot.commands.generalcommands;

import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;

/**
 * Represents a command that prints a help message regarding what commands are available to the user.
 */
public class HelpCommand extends Command {
    public static final String HELP_MESSAGE = "Here is the list of things I think I can do...ㅜㅅㅜ\n"
            + "   todo your_task_name (optional)#tag1_name #tag2_name...\n"
            + "   deadline your_task_name /by your_deadline\n"
            + "   event your_task_name /from starting_time /to end_time\n"
            + "   find your_task_name\n"
            + "   find tag1_name tag2_name...\n"
            + "   list\n"
            + "   delete task_index\n"
            + "   mark task_index\n"
            + "   unmark task_index\n"
            + "   help\n"
            + "   bye";
    @Override
    public CommandResult execute() throws Exception {
        return new CommandResult(HELP_MESSAGE);
    }
}
