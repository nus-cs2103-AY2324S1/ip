package thea;

/**
 * Represents an executable user command which exits the program.
 * This class is a subclass of the abstract class Command with abstract method execute.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a new ExitCommand object.
     * IsExit is true as this is an ExitCommand.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Print the exiting message and notify Main
     * to exit the program.
     * Saves the taskList before exiting.
     *
     * @param thea reference to the chatbot containing relevant data.
     */
    @Override
    public String execute(Thea thea) {
        thea.storage.saveTaskList(thea.tasks);
        return thea.ui.exit();
    }
}
