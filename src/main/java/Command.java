/**
 * Represents an abstract Command class.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public abstract class Command {
    /** Boolean determining whether user wants to exist the chatbot. */
    private boolean isExit;

    /**
     * Constructor for Command class.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Returns the isExit boolean to let the chatbot know whether
     * to exit.
     *
     * @return isExit boolean.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Set the isExit boolean to true so that the chatbot will exit.
     */
    public void setExit() {
        this.isExit = true;
    }

    /**
     * Abstract method that executes the command
     *
     * @param tasks TaskList class storing an ArrayList of Task objects.
     * @param ui Ui class to handle user interactions.
     * @param storage Storage class to read and store tasks by the user.
     * @throws ChatterException Any error that occurs due to the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ChatterException;
}
