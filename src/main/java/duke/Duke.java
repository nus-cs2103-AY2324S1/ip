package duke;

import duke.command.Command;
import duke.command.SetCommand;
import duke.exception.KoraException;
import duke.list.CommandList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.list.TaskList;
import duke.ui.Ui;


/**
 * Main class of Kora Chatbot Program.
 */
public class Duke {

    private final TaskList taskList;
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
        storageTask = new Storage("./data/savedtask.txt");
        storageCommand = new Storage("./data/savedCommand.txt");
        taskList = new TaskList();
        commandList = new CommandList();
        parser = new Parser(commandList);
        try {
            storageTask.loadTask(taskList);
            storageCommand.loadCommand(commandList);
        } catch (KoraException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Executes and prints command.
     * @param userInput Input from the user.
     * @return Command.
     */
    public Command getResponse(String userInput) {
        try {
            Command command = parser.parse(userInput);
            if (command.isSetCommand()) {
                //SetCommand setCommand = (SetCommand) command;
                command.executeSet(commandList, storageCommand);
            } else {
                command.execute(taskList, storageTask);
            }
            command.printOutput(command.getCommandMessage());
            return command;
        } catch (KoraException e) {
            System.out.println("la");
            return null;
        }
    }

    /**
     * Runs main function of Kora Chatbot.
     */
    public void run() {
        ui.greet();
        while (!isExit) {
            String userInput = ui.getUserInput();
            Command command = getResponse(userInput);
            if (command == null) {
                isExit = false;
            } else {
                isExit = command.isExit();
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
        try {
            Duke kora = new Duke();
            kora.run();
        } catch (Exception e) {
            System.out.println("haha,,");
        }

    }
}
