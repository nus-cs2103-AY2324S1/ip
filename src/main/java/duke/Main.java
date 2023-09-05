package duke;

import duke.exception.DukeStorageException;
import duke.service.CliParserService;
import duke.service.CommandFactory;
import duke.service.OutputService;
import duke.service.StorageService;
import duke.service.TaskFactory;
import duke.service.UiService;

/**
 * The Main class serves as the primary entry point for the Duke application.
 * It initializes necessary services, dependencies, and orchestrates the bootstrapping
 * process for the application's Command-Line Interface (CLI).
 * <p>
 * Responsibilities of this class include:
 * <ul>
 *     <li>Setting up necessary services like UI, storage, and task management.</li>
 *     <li>Handling the bootstrapping process, including exception handling during initialization.</li>
 *     <li>Starting the CLI parser to begin processing user inputs.</li>
 * </ul>
 * </p>
 */
public class Main {

    /**
     * The main method serves as the starting point for the Duke application.
     * It begins by initializing the core services. If the storage service indicates any
     * issues (e.g., corrupted files), appropriate messages are displayed to the user.
     * <p>
     * Once initialization is successful, it greets the user and starts the CLI parser
     * service to listen and process user inputs until termination.
     * </p>
     *
     * @param args Command line arguments, not currently utilized in this application.
     */
    public static void main(String[] args) {
        OutputService outputService = new OutputService();
        UiService uiService = new UiService(outputService);
        try {
            StorageService storageService = new StorageService();
            if (storageService.wasFileCorrupted()) {
                uiService.printStorageFileCorrupted();
            }
            Duke changooseBot = new Duke("Changoose", storageService);
            TaskFactory taskFactory = new TaskFactory();
            CommandFactory commandFactory = new CommandFactory(taskFactory, changooseBot, uiService);
            CliParserService cliParserService = new CliParserService(uiService, commandFactory);

            uiService.printGreet(changooseBot.getBotName());
            cliParserService.parse();
            uiService.printBye();
        } catch (DukeStorageException e) {
            uiService.printStorageInitializationFailure();
        }
    }
}
