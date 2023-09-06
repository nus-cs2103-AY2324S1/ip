package rayshawn.chatbot.commands;

import rayshawn.chatbot.parser.Parser;
import rayshawn.chatbot.storage.Storage;
import rayshawn.chatbot.tasks.TaskList;

/**
 * This class is used to hold the logic for executing tasks.
 */
public class Logic {
    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;

    /**
     * Constructor for Logic.
     */
    public Logic() {
        try {
            storage = new Storage();
            tasks = storage.loadTasks();
            parser = new Parser();
        } catch (Storage.InvalidStorageFilePathException | Storage.StorageOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public Command getCommand(String commandText) {
        return parser.parseCommand(commandText);
    }

    /**
     * Used to obtain the result of the different commands.
     *
     * @param command to get result from
     * @return result of command
     */
    public CommandResult executeCommand(Command command) {
        CommandResult commandResult;
        command.setTaskList(tasks);
        commandResult = command.execute();

        try {
            storage.save(tasks);
        } catch (Storage.StorageOperationException e) {
            throw new RuntimeException(e);
        }

        return commandResult;
    }



}
