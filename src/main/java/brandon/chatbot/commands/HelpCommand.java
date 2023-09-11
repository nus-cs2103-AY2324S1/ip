package brandon.chatbot.commands;

public class HelpCommand extends Command {
    public final static String HELP_MESSAGE = "Here is the list of things I can do...\n" +
            "   todo <your task name>\n" +
            "   deadline <your task name> /by <your deadline>\n" +
            "   event <your task name> /from <starting time> /to <end time>\n" +
            "   list\n" +
            "   delete <task index from your list>\n" +
            "   mark <task index from your list>\n" +
            "   unmark <task index from your list>\n" +
            "   help\n" +
            "   bye";
    @Override
    public CommandResult execute() throws Exception {
        return new CommandResult(HELP_MESSAGE);
    }
}
