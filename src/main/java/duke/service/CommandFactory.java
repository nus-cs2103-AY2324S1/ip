package duke.service;

import duke.Duke;
import duke.commands.*;
import duke.exception.InvalidCommandInputException;
import duke.exception.UnknownCommandException;

import java.util.List;

public class CommandFactory {
    private TaskFactory taskFactory;
    private Duke dukeBot;
    private UiService uiService;

    public CommandFactory(TaskFactory taskFactory, Duke dukeBot, UiService uiService) {
        this.taskFactory = taskFactory;
        this.dukeBot = dukeBot;
        this.uiService = uiService;
    }

    public Command createCommand(String command, List<String> args)
            throws UnknownCommandException, InvalidCommandInputException {
        switch (command) {
            case "bye":
                return new ExitCommand(dukeBot, uiService);
            case "list":
                return new ListCommand(dukeBot, uiService);
            case "mark":
                checkValidTaskNumberArgument(args);
                int markId = Integer.parseInt(args.get(0));
                return new MarkTaskCommand(dukeBot, uiService, markId);
            case "unmark":
                checkValidTaskNumberArgument(args);
                int unmarkId = Integer.parseInt(args.get(0));
                return new UnmarkTaskCommand(dukeBot, uiService, unmarkId);
            case "delete":
                checkValidTaskNumberArgument(args);
                int deleteId = Integer.parseInt(args.get(0));
                return new DeleteTaskCommand(dukeBot, uiService, deleteId);
            case "todo":
            case "deadline":
            case "event":
                return new AddTaskCommand(dukeBot, uiService, command, args, taskFactory);
            default:
                throw new UnknownCommandException(String.format("Command: %s not recognized! :<", command));
        }
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void checkValidTaskNumberArgument(List<String> argsList) throws InvalidCommandInputException {
        if (argsList.isEmpty() || !isNumeric(argsList.get(0))) {
            throw new InvalidCommandInputException("A numeric argument should be provided.");
        }
    }
}
