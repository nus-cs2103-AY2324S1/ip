package task;

/**
 * The `Add` class represents an "add" task in the BloopBot application.
 * It extends the base `Task` class and inherits its properties and methods.
 * An `Add` task includes a description and can be marked as completed or not completed.
 * This class is used to represent tasks created using the "add" command.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class Add extends Task {

    /**
     * Constructs a new `Add` task with the specified description.
     *
     * @param description The description of the `Add` task.
     */
    public Add(String description) {
        super(description);
    }
}
