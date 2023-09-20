package duke;

import duke.command.Command;
import duke.exception.KoraException;
import duke.list.CommandList;
import duke.parser.Parser;
import duke.list.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;


/**
 * Main class of Kora Chatbot Program.
 */
public class Duke {

    private final TaskList mainTaskList;
    private Storage storageTask;
    private Storage storageCommand;
    private Ui ui;
    private boolean isExit = false;
    private final CommandList commandList;

    private Parser parser;


    /**
     * Class constructor of Duke.
     */
    public Duke() {
        ui = new Ui();

        mainTaskList = new TaskList();
        commandList = new CommandList();
        parser = new Parser(commandList);
        try {
            storageTask = new Storage("./data/savedtask.txt");
            storageCommand = new Storage("./data/savedCommand.txt");
            mainTaskList.addTaskList(storageTask.loadTask());
            commandList.addCommandList(storageCommand.loadCommand());
        } catch (KoraException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Executes and prints command.
     * @param userInput Input from the user.
     * @return Command.
     */
    public String getResponse(String userInput) {
        try {
            Command command = parser.parse(userInput);
            if (command.isFileCommand()) {
                storageTask = new Storage(command.getFilePath());
            }
            if (command.isSetCommand()) {
                //SetCommand setCommand = (SetCommand) command;
                command.executeSet(commandList, storageCommand);
            } else {
                command.execute(mainTaskList, storageTask);
            }
            //command.printOutput(command.getCommandMessage());
            return command.getCommandMessage();
        } catch (KoraException e) {
            return e.getMessage();
        }
    }

    /**
     * Runs main function of Kora Chatbot.
     */
    public void run() {
        ui.greet();
        while (!isExit) {
            String userInput = ui.getUserInput();
            Command command;
            try {
                command = parser.parse(userInput);
                if (command.isFileCommand()) {
                    storageTask = new Storage(command.getFilePath());
                }
                if (command.isSetCommand()) {
                    command.executeSet(commandList, storageCommand);
                } else {
                    command.execute(mainTaskList, storageTask);
                }
                command.printOutput(command.getCommandMessage());
                isExit = command.isExit();
            } catch (KoraException e) {
                ui.koraReply(e.getMessage());
            }
        }
        ui.closeScanner();
    }

    public String getGreeting() {
        return ui.getGreetMessage();
    }


    /**
     * Runs the main programme.
     * @param args The command-line arguments
     */
    public static void main(String[] args) {
        Duke kora = new Duke();
        kora.run();
    }
}
