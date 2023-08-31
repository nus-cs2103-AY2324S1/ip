package commands;

import java.util.Dictionary;
import java.util.Hashtable;

import client.Rock;

public class Commands {
    Dictionary<String, Command> commandList;
    public Commands(Rock client) {
        this.commandList = new Hashtable<String, Command>() {{
            put("bye", new CommandBye(client));
            put("list", new CommandTaskList(client));
            put("mark", new CommandTaskMark(client, true));
            put("unmark", new CommandTaskMark(client, false));
            put("todo", new CommandTaskCreate(client, TaskTypes.TODO));
            put("deadline" ,new CommandTaskCreate(client, TaskTypes.DEADLINE));
            put("event", new CommandTaskCreate(client, TaskTypes.EVENT));
            put("delete", new CommandTaskDelete(client));
            put("listbydate", new CommandTaskListByDate(client));
            put("find", new CommandTaskFind(client));
    }};
    }
    /** Lookup table of possible commands that can be used. */

    /**
     * Method used to get the appropriate command from a keyword
     * @param keyword Keyword used to identify each command
     * @return Command corresponding to the keyword
     * @throws IllegalArgumentException Thrown when an unknown command is given.
     */
    public Command getCommand(String keyword) throws IllegalArgumentException{
        Command command = this.commandList.get(keyword);
        if (command == null) throw new IllegalArgumentException("Unknown command!");
        return command;    
    }
}
