package catbot.bot;

import catbot.io.UserIo;

/**
 * Object that contains the full supported feature set of an assistant.
 */
public interface Bot {

    void initialize(UserIo userIo);

    void run(CommandArgumentStruct commandArgumentStruct);

}
