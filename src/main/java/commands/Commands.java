package commands;

import java.util.Dictionary;
import java.util.Hashtable;

import client.Rock;
/**
 * Represents list of legal commands
 * that can be called by user.
 * @author Alvis Ng (supermii2)
 */
public class Commands {
    /** Lookup table of commands callable by user */
    private Dictionary<String, Command> commandList;
    /**
     * Constructor to initialise the
     * list of callable commands
     * @param client Chatbot object
     */
    public Commands(Rock client) {
        this.commandList = new Hashtable<String, Command>() {{
                put("bye", new CommandBye(client));
                put("list", new CommandTaskList(client));
                put("mark", new CommandTaskMark(client, true));
                put("unmark", new CommandTaskMark(client, false));
                put("todo", new CommandTaskCreate(client, TaskTypes.TODO));
                put("deadline", new CommandTaskCreate(client, TaskTypes.DEADLINE));
                put("event", new CommandTaskCreate(client, TaskTypes.EVENT));
                put("delete", new CommandTaskDelete(client));
                put("listbydate", new CommandTaskListByDate(client));
                }};
    }
    /**
     * Method used to get the appropriate command from a keyword
     * @param keyword Keyword used to identify each command
     * @return Command corresponding to the keyword
     * @throws IllegalArgumentException Thrown when an unknown command is given.
     */
    public Command getCommand(String keyword) throws IllegalArgumentException {
        Command command = this.commandList.get(keyword);
        if (command == null) {
            throw new IllegalArgumentException("Unknown command!");
        }
        return command;
    }
}
