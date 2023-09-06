package catbot.internal;

import java.util.HashMap;

public class CommandMap {

    private final HashMap<String, Command> commandMap = new HashMap<>();
    private Command defaultCommand;
    //endregion

    public CommandMap addCommand(String invocation, Command lambda) {
        commandMap.put(invocation, lambda);
        return this;
    }

    public CommandMap setDefaultCommand(Command defaultCommand) {
        this.defaultCommand = defaultCommand;
        return this;
    }

    public void run(String s, String args) {
        commandMap.getOrDefault(s, defaultCommand).run(args);
    }
}
