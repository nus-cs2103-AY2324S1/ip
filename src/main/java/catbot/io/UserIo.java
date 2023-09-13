package catbot.io;

public interface UserIo extends ErrorIndicatorIo, TaskAssistantIo {

    /**
     * Initialize IO channel.
     * Intended to open resources, or send a welcome message.
     * */
    void initialize();

    /**
     * Cleanup IO channel.
     * Intended to close resources, or send a goodbye message.
     */
    void cleanup();

    /**
     * Simple container for a command and argument pair, both stored as Strings.
     */
    class CommandArgumentStruct {
        private final String command;
        private final String argument;

        public CommandArgumentStruct(String command, String argument) {
            this.command = command;
            this.argument = argument;
        }

        public String getArgument() {
            return argument;
        }

        public String getCommand() {
            return command;
        }
    }

    /**
     * Get the next command (valid or invalid).
     *
     * @return {@link CommandArgumentStruct CommandArgumentStruct} containing the command and its argument.
     */
    CommandArgumentStruct getNextCommand();
}
