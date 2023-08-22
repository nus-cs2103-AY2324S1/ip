package tasks;

public class TodoTask extends ShibaTask {
    /**
     * Parses a TodoTask from a command.
     * @param cmd The command to be parsed.
     * @return The TodoTask parsed from the command, or null if the command is invalid.
     */
    public static TodoTask fromCmd(String cmd) {
        String[] cmdSplit = cmd.split(" ", 2);
        if (cmdSplit.length != 2) {
            return null;
        }

        return new TodoTask(cmdSplit[1]);
    }

    public TodoTask(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
