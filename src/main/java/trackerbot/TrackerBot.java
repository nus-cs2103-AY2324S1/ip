package trackerbot;

import trackerbot.command.Command;
import trackerbot.exception.TrackerBotException;
import trackerbot.task.TaskList;
import trackerbot.utils.Parser;
import trackerbot.utils.Storage;
import trackerbot.utils.Ui;

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
    private Ui ui;

    /**
     * Constructor for the TrackerBot instance.
     *
     * @param appName The name of the app to instantiate.
     */
    public TrackerBot(String appName) {
        tasks = new TaskList();
        ui = Ui.instantiate(appName);
    }

    /**
     * Calls the Ui to read commands, create Commands and execute the command.
     *
     * @return If the program has requested to exit.
     */
    private boolean handleInput() {
        Command command = Parser.parseCommand(ui.readCommand());
        try {
            ui.showLine();
            command.execute(tasks, ui);
        } catch (TrackerBotException e) {
            ui.showError(e.getMessage());
        } finally {
            ui.showLine();
        }

        return command.isExit();
    }

    /** Starts the app. */
    public void run() {
        try {
            Storage.read(tasks);
        } catch (TrackerBotException e) {
            ui.showError(e.getMessage());
        }

        boolean isBye;
        do {
            isBye = handleInput();
        } while (!isBye);

        try {
            Storage.save(tasks);
        } catch (TrackerBotException e) {
            ui.showError(e.getMessage());
        }
    }

    /** Entry point. **/
    public static void main(String[] args) {
        new TrackerBot(APP_NAME).run();
    }
}
