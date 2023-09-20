package catbot.bot;

import catbot.io.UserIo;

/**
 * Object that contains the full supported feature set of an assistant.
 */
public interface Bot {

    /**
     * Sets up the bot for use.
     * Intended to include the creation of commands.
     *
     * @param userIo the IO object the bot will use to communicate with the user.
     */
    void initialize(UserIo userIo);

    /**
     * Runs a command based on the given command and argument, if applicable.
     * If a matching command does not exist, may run a default command.
     * Any such behaviour is defined by the bot.
     *
     * @param commandArgumentStruct a struct containing information about the command to run, and its arguments.
     */
    void run(CommandArgumentStruct commandArgumentStruct);

}
