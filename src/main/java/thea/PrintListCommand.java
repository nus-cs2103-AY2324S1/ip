package thea;

/**
 * Represents an executable command that prints the current task list.
 * This class is a subclass of the abstract class Command with abstract method execute.
 */
public class PrintListCommand extends Command {

    /**
     * Constructs a new MarkCommand object.
     */
    public PrintListCommand() {
        super(false);
    }

    /**
     * Prints the current task list.
     *
     * @param thea reference to the chatbot containing relevant data.
     */
    @Override
    public String execute(Thea thea) {
        return thea.ui.printList(thea.tasks);
    }
}
