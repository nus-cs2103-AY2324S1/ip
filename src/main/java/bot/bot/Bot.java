package bot.bot;

import bot.command.Command;
import bot.command.ControlFlow;
import bot.exception.DateTimeParseBotException;
import bot.exception.FileErrorBotException;
import bot.exception.IllegalExpressionBotException;
import bot.exception.IncompleteBotException;
import bot.storage.Storage;
import bot.task.TaskList;

import java.io.IOException;

public class Bot {

    private TaskList taskList;
    private ControlFlow controlFlow;

    private static final String GREETINGS = "________________________________________\n" +
            "Hello! I'm Bot\n" +
            "What can I do for you?\n" +
            "________________________________________";


    public Bot() {
        try {
            taskList = Storage.read();
            controlFlow = new ControlFlow(taskList);
        } catch (DateTimeParseBotException | IOException | FileErrorBotException e) {
            System.out.println(e);
        }

    }

    public Command getCommand(String string) throws DateTimeParseBotException,
            IllegalExpressionBotException, IncompleteBotException, FileErrorBotException, IOException {
        Command command = controlFlow.execute(string);
        return command;
    }

    public String getGreeting() {
        return Bot.GREETINGS;
    }

}
