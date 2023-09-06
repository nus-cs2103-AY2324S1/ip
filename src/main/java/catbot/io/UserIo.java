package catbot.io;

import catbot.internal.NamedParameterMap;

public interface UserIo extends ErrorIndicatorIo, TaskAssistantIo {

    /**
     * Initialize IO channel, eg welcome message.
     * */
    void initialize();

    /**
     * Cleanup IO channel, eg goodbye message.
     */
    void cleanup();

    /**
     * Simple container with command and argument as strings
     */
    class CommandArgument {
        private final String command;
        private final String argument;

        public CommandArgument(String command, String argument) {
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
     * @return container with command and argument as strings
     */
    CommandArgument getNextCommand();
}
