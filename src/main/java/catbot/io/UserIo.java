package catbot.io;

import catbot.bot.Bot;

/**
 * Object that implements the full expected functionality of IO expected of a CatBot assistant.
 */
public interface UserIo extends ErrorIndicatorIo, TaskAssistantIo {

    /**
     * Initializes IO channel.
     * Intended to open resources, or send a welcome message.
     * */
    void initialize();

    /**
     * Cleans up the IO channel.
     * Intended to close resources, or send a goodbye message.
     */
    void cleanup();

    /**
     * Returns a boolean describing whether the io channel is still open.
     * Expected to be true after {@link #initialize() initialize}, and false after {@link #cleanup()}.
     *
     * @return true if the io can still be used to communicate to the user, and false otherwise.
     */
    boolean isStillOpen();

    /**
     * Allows the IO object to take over logic to respond to user input.
     * A simple example is a while loop that uses a Scanner to wait for user interaction.
     * Intended to also work for event handler designs.
     */
    void takeoverExecutionLogic(Bot bot);
}
