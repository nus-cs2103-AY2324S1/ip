package Duke;
import java.util.HashMap;

import Command.Commandable;
import Command.ShutdownCommand;
import Command.HelpCommand;

/**
 *
 *
 */
public class Parser {
    HashMap<String, Commandable> stringToCommand;
    Duke caller;
    public Parser(Duke caller) {
        this.caller = caller;
        stringToCommand = new HashMap<>();
        init();
    }
    private void init() {
        stringToCommand.put("bye", new ShutdownCommand());
        stringToCommand.put("help", new HelpCommand());
        stringToCommand.put("todo", new TodoCommand());
        stringToCommmand.put()
    }
}
