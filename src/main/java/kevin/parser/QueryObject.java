package kevin.parser;

import java.util.ArrayList;

public class QueryObject {
    private final Commands commandType;
    private final ArrayList<String> args;

    public QueryObject(Commands commandType, ArrayList<String> args) {
        this.commandType = commandType;
        this.args = args;
    }

    public Commands getCommandType() {
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
