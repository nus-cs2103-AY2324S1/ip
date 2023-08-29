package rayshawn.chatbot;

import rayshawn.chatbot.commands.ByeCommand;
import rayshawn.chatbot.commands.Command;
import rayshawn.chatbot.commands.CommandResult;
import rayshawn.chatbot.parser.Parser;
import rayshawn.chatbot.storage.Storage;
import rayshawn.chatbot.storage.Storage.InvalidStorageFilePathException;
import rayshawn.chatbot.storage.Storage.StorageOperationException;
import rayshawn.chatbot.tasks.TaskList;
import rayshawn.chatbot.ui.Ui;

public class Main {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static void main(String[] args) {
        new Main().run(args);
    }

    public void run(String[] args) {
        start(args);
        runCommandLoopUntilByeCommand();
        exit();
    }

    public void start(String[] args) {
        try {
            this.ui = new Ui();
            this.storage = initializeStorage(args);
            this.tasks = storage.load();
            ui.showWelcomeMessage();
        } catch (InvalidStorageFilePathException | StorageOperationException e) {
            ui.showInitFailedMessage();
            throw new RuntimeException(e);
        }
    }

    public void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }

    private void runCommandLoopUntilByeCommand() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = new Parser().parseCommand(userCommandText);
            CommandResult result = executeCommand(command);
            ui.showResultToUser(result);
        } while(!ByeCommand.isBye(command));
    }

    private CommandResult executeCommand(Command command) {
        try {
            command.setTaskList(tasks);
            CommandResult result = command.execute();
            storage.save(tasks);
            return result;
        } catch (Exception e){
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private Storage initializeStorage(String[] args) throws InvalidStorageFilePathException {
        boolean isStorageFileSpecifiedByUser = args.length > 0;
        return isStorageFileSpecifiedByUser ? new Storage(args[0]) : new Storage();
    }
}
