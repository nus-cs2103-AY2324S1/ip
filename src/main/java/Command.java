import java.util.Dictionary;
import java.util.Hashtable;
import java.util.function.Consumer;
/**
 * Representation of a command
 * that can be issued to the chatbot.
 * 
 * @author Alvis Ng (supermii2)
 */
public abstract class Command implements Consumer<Parser> {
    /** Code to be run when command is called. */
    public abstract void accept(Parser input);
    /** Lookup table of possible commands that can be used. */
    private static Dictionary<String, Command> COMMAND_LIST = new Hashtable<String, Command>() {{
        put("bye", new CommandBye());
        put("list", new CommandTaskList());
        put("mark", new CommandTaskMark(true));
        put("unmark", new CommandTaskMark(false));
        put("todo", new CommandTaskCreate(TaskTypes.TODO));
        put("deadline" ,new CommandTaskCreate(TaskTypes.DEADLINE));
        put("event", new CommandTaskCreate(TaskTypes.EVENT));
        put("delete", new CommandTaskDelete());
    }};
    /**
     * Method used to get the appropriate command from a keyword
     * @param keyword Keyword used to identify each command
     * @return Command corresponding to the keyword
     * @throws IllegalArgumentException Thrown when an unknown command is given.
     */
    public static Command getCommand(String keyword) throws IllegalArgumentException{
        Command command = COMMAND_LIST.get(keyword);
        if (command == null) throw new IllegalArgumentException("Unknown command!");
        return command;
    }
}