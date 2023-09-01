package kevin.parser;

import java.util.ArrayList;

/**
 * A class that contains the Command and arguments to be passed to the Evaluator.
 */
public class QueryObject {
    private final Commands commandType;
    private final ArrayList<String> args;

    /**
     * Constructor to initialize QueryObject
     * @param commandType This is the Command for the query object.
     * @param args This is the arguments needed to run the command.
     */
    public QueryObject(Commands commandType, ArrayList<String> args) {
        this.commandType = commandType;
        this.args = args;
    }

    /**
     * Gets the command of the query object.
     */
    public Commands getCommandType() {
        return commandType;
    }

    /**
     * Gets the arguments of the query object.
     */
    public ArrayList<String> getArgs() {
        return args;
    }

    /**
     * {@inheritDoc}
     * @return Returns a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(this.commandType.name());
        for (int i = 0; i < args.size(); i++) {
            result.append(args.get(i));
        }
        return result.toString();
    }
}
