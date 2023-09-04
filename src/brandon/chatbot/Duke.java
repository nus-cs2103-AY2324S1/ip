import java.io.IOException;
import java.util.Locale;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import commands.Command;
import commands.CommandResult;
import commands.ExitCommand;
import commands.UnknownCommand;
import parser.Parser;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;

import ui.TextUi;
import common.DukeException;
public class Duke {
    public static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static Path outputPath = Paths.get(CURRENT_DIRECTORY, "output.txt");
    private TaskList tasks;
    private TextUi ui;
    private Storage storage;
    /**
     *
     * @throws IOException
     */
    public void greeting() throws Exception {
        this.ui = new TextUi();
        tasks = new TaskList();
        storage = new Storage(outputPath);
        ui.showWelcomeMessage();
        parseAndRun();
        System.out.println("    bye...");
        System.exit(1);
    }
    public void parseAndRun() throws DukeException {
        Command parsedCommand;
        do {
            String userCommand = ui.getUserCommand();
            parsedCommand = new Parser().parseCommand(userCommand);
            CommandResult result = executeCommand(parsedCommand);
            ui.showResultToUser(result);
        } while (!ExitCommand.isExit(parsedCommand));
    }
    private CommandResult executeCommand(Command command) {
        try {
            command.setData(tasks);
            CommandResult result = command.execute();
            storage.save(tasks);
            return result;
        } catch (Exception e) {
            return new UnknownCommand().execute();
        }
    }
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.ENGLISH);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        new Duke().greeting();
    }
}

