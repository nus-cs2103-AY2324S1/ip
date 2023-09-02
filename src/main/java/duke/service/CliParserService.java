package duke.service;

import duke.commands.Command;
import duke.exception.*;

import java.util.*;

public class CliParserService {
    private final UiService uiService;
    private final CommandFactory commandFactory;

    public CliParserService(UiService uiService, CommandFactory commandFactory) {
        this.uiService = uiService;
        this.commandFactory = commandFactory;
    }

    public void parse() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine().trim();
            List<String> arguments = new ArrayList<>();

            // Split the command and its primary argument
            String[] splitBySpace = line.split(" ", 2);
            String commandType = splitBySpace[0];

            // If there's more than just the command, handle the arguments
            if (splitBySpace.length > 1) {
                // primaryArg refers to either task name, or the task index.
                String primaryArg = splitBySpace[1].split("/")[0].trim();
                arguments.add(primaryArg);

                // Split the rest by slashes
                String[] splitBySlash = splitBySpace[1].split("/");
                for (int i = 1; i < splitBySlash.length; ++i) { // Start at 1 to skip the task name
                    arguments.add(splitBySlash[i].trim());
                }
            }
            try {
                Command command = commandFactory.createCommand(commandType, arguments);
                if (command.isExit()) {
                    return;
                }
                command.execute();
            } catch (UnknownCommandException | InvalidCommandInputException e) {
                uiService.printGenericMessage(e.getMessage());
            }
        }
    }
}
