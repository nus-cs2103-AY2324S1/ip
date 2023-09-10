package brandon.chatbot;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;
import brandon.chatbot.commands.ExitCommand;
import brandon.chatbot.commands.UnknownCommand;
import brandon.chatbot.parser.Parser;
import brandon.chatbot.storage.Storage;
import brandon.chatbot.tasks.TaskList;
import brandon.chatbot.ui.TextUi;


/**
 * Entry point of the Duke chatbot application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    public static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static Path outputPath = Paths.get(CURRENT_DIRECTORY, "output.txt");
    private TaskList tasks = new TaskList();
    private Storage storage;

    /**
     * Sets up TextUi, TaskList, and Storage used for the chatbot.
     */
//    public void greeting() {
//        Locale.setDefault(Locale.ENGLISH);
//
//        this.ui = new TextUi();
//        tasks = new TaskList();
//        storage = new Storage(outputPath);
//        ui.showWelcomeMessage();
//        parseAndRun();
//        System.out.println("    bye...");
//        System.exit(1);
//    }

    /**
     * Parses the input, convert it into Command instance, then runs the program until termination.
     */
//    public void parseAndRun() {
//        Command parsedCommand;
//        do {
//            String userCommand = ui.getUserCommand();
//        } while (!ExitCommand.isExit(parsedCommand));
//    }
    private CommandResult executeCommand(Command command) throws Exception {
        try {
            command.setData(tasks);
            CommandResult result = command.execute();
//            storage.save(tasks);
            return result;
        } catch (Exception e) {
            return new UnknownCommand().execute();
        }
    }

    public String getResponse(String input) throws Exception {
        Command parsedCommand = new Parser().parseCommand(input);
        CommandResult result = executeCommand(parsedCommand);
        return result.feedbackToUser;
    }
}

