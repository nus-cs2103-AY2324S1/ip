/**
 * Represents a Command class that is responsible for exiting the chatbot.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class ExitCommand extends Command{
    /**
     * Constructor for the ExitCommand class for users to exit the chatbot.
     */
    public ExitCommand() {
        super();
    }

    /**
     *
     *
     * @param tasks TaskList class storing an ArrayList of Task objects.
     * @param ui Ui class to handle user interactions.
     * @param storage Storage class to read and store tasks by the user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.setExit();
        ui.showExit();
    }
}
