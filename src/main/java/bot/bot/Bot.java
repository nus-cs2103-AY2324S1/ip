package bot.bot;

import bot.command.Command;
import bot.command.ControlFlow;
import bot.exception.DateTimeParseBotException;
import bot.exception.FileErrorBotException;
import bot.exception.IllegalExpressionBotException;
import bot.exception.IncompleteBotException;
import bot.storage.Storage;
import bot.task.TaskList;

public class Bot {

    private TaskList taskList;
    private ControlFlow controlFlow;

    private static final String GREETINGS = "________________________________________\n" +
            "Hello! I'm Bot\n" +
            "What can I do for you?\n" +
            "________________________________________";

    /**
     * Creates an instance of a Bot object
     */
    public Bot() {
        try {
            taskList = Storage.read();
            controlFlow = new ControlFlow(taskList);
        } catch (DateTimeParseBotException | FileErrorBotException e) {
            System.out.println(e);
        }

    }

    /**
     * Returns the Command object corresponding to the user input from the GUI
     *
     * @param string the user input
     * @return Command object based on user input
     * @throws DateTimeParseBotException if Datetime from user input is in wrong format
     * @throws IllegalExpressionBotException if the input is not correct
     * @throws IncompleteBotException if the input for the corresponding task is incomplete
     * @throws FileErrorBotException if the file could not be accessed
     */
    public Command getCommand(String string) throws DateTimeParseBotException,
            IllegalExpressionBotException, IncompleteBotException, FileErrorBotException {
        return controlFlow.execute(string);
    }

    /**
     * Returns the user greeting to be displayed when app launch
     *
     * @return String of the user greeting
     */
    public String getGreeting() {
        return Bot.GREETINGS;
    }

}
