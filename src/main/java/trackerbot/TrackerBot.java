package trackerbot;

import trackerbot.command.Command;
import trackerbot.exception.TrackerBotException;
import trackerbot.task.TaskList;
import trackerbot.utils.Parser;
import trackerbot.utils.Storage;
import trackerbot.gui.UiHandler;

/**
 * Main Program for the application. <br>
 * As of Level-0, this has been renamed from Duke to TrackerBot
 * as part of the requirements for the iP.
 *
 * @author WZWren
 * @version A-JavaDoc
 */
public class TrackerBot {
    /** Name of the app. **/
    private static final String APP_NAME = "TrackerBot";

    /** Collection of tasks stored by TrackerBot */
    private TaskList tasks;

    /** Displays user IO. */
    private UiHandler uiHandler;

    /**
     * Constructor for the TrackerBot instance.
     *
     * @param appName The name of the app to instantiate.
     */
    private TrackerBot(String appName) {
        tasks = new TaskList();
        uiHandler = UiHandler.instantiate(appName);
    }

    /**
     * Instantiates the TrackerBot object.
     *
     * @return The TrackerBot instance, with loaded data in the Task List, if any.
     */
    public static TrackerBot instantiate() {
        TrackerBot instance = new TrackerBot(APP_NAME);

        try {
            Storage.read(instance.tasks);
        } catch (TrackerBotException e) {
            instance.uiHandler.setError("I failed to retrieve your save file. "
                    + "Here's the error I got: \n" + e.getMessage());
        }

        return instance;
    }

    public void handleSave() throws TrackerBotException {
        Storage.save(tasks);
    }

    /**
     * Returns the message from the TrackerBot.
     *
     * @return The message, stored in the UiHandler instance.
     */
    public String getLastMessage() {
        return uiHandler.getMessage();
    }

    /**
     * Parses a Command and executes it.
     *
     * @return The post-execution String in our UiHandler.
     */
    public String handleInput(String input) {
        Command command = Parser.parseCommand(input);
        try {
            // TODO: This is a bit weird... Why is there different levels of handling on uiHandler?
            command.execute(tasks, uiHandler);
        } catch (TrackerBotException e) {
            uiHandler.setError(e.getMessage());
        }

        return uiHandler.getMessage();
    }
}
