import java.util.HashMap;

public class CommandMap {

    private HashMap<String, Command> commandMap;
    //endregion

    public void addCommand(String invocation, Command lambda) {
        commandMap.put(invocation, lambda);
    }

    public void run(String s, String args) {
        commandMap.get(s).run(args);
    }
}
