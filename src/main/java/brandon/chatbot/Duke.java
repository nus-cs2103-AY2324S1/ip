package brandon.chatbot;

import java.nio.file.Path;
import java.nio.file.Paths;

import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;
import brandon.chatbot.commands.generalcommands.UnknownCommand;
import brandon.chatbot.parser.Parser;
import brandon.chatbot.storage.Storage;
import brandon.chatbot.tag.TagTaskMap;
import brandon.chatbot.tasks.TaskList;



/**
 * Entry point of the Duke chatbot application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    public static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static Path outputPath = Paths.get(CURRENT_DIRECTORY, "output.txt");
    private TaskList tasks = new TaskList();
    private TagTaskMap tagTaskMap = new TagTaskMap();
    private Storage storage = new Storage(outputPath);

    private CommandResult executeCommand(Command command) {
        try {
            command.setData(tasks, tagTaskMap);
            CommandResult result = command.execute();
            storage.save(tasks);
            return result;
        } catch (Exception e) {
            return new UnknownCommand(e.getMessage()).execute();
        }
    }

    public String getResponse(String input) {
        Command parsedCommand = new Parser().parseCommand(input);
        CommandResult result = executeCommand(parsedCommand);
        return result.feedbackToUser;
    }
}

