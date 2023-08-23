public enum Commands {
    // BYE("bye"), //Bye is not necessary because it is handled by the while loop, however, it is good to have it here just to note
    LIST("list"),
    MARK("mark", 2),
    UNMARK("unmark", 2);

    public final String commandString;
    public final int commandParamsLen;

    private Commands(String commandString) {
        this.commandString = commandString;
        this.commandParamsLen = 1;
    }

    private Commands(String commandString, int commandParamsLen) {
        this.commandString = commandString;
        this.commandParamsLen = commandParamsLen;
    }

    public static Commands parseCommand(String rawInput) {
        String[] commandSplit = rawInput.trim().split(" ", 2);
        String command = commandSplit[0];
        for (Commands c : values()) {
            if (c.commandString.equalsIgnoreCase(command) && commandSplit.length == c.commandParamsLen) {
                if (commandSplit.length == 2) {
                    try {
                        Integer.parseInt(commandSplit[1]);
                    }
                    catch (NumberFormatException e) {
                        return null;
                    }
                }
                return c;
            }
        }
        return null;
    }
}