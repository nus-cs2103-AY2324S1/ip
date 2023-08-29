package Parser;

import java.util.ArrayList;

public class QueryObject {
    private final Commands commandType;
    private final ArrayList<String> args;

    public QueryObject(Commands commandType, ArrayList<String> args) {
        this.commandType = commandType;
        this.args = args;
    }

    public Commands getCommandType() {
        return this.commandType;
    }

    public ArrayList<String> getArgs() {
        return this.args;
    }
}
