package kevin.parser;

import java.util.ArrayList;

public class QueryObject {
    private final Command commandType;
    private final ArrayList<String> args;

    public QueryObject(Command commandType, ArrayList<String> args) {
        this.commandType = commandType;
        this.args = args;
    }

    public Command getCommandType() {
        return commandType;
    }

    public ArrayList<String> getArgs() {
        return args;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(this.commandType.name());
        for (int i = 0; i < args.size(); i++) {
            result.append(args.get(i));
        }
        return result.toString();
    }
}
