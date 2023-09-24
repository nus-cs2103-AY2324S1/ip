package echobot.utilities;

/**
 * Class to declare a Deadline task
 */
public class Deadline extends Task {

    /**
     * Creates new instance of a deadline task
     *
     * @param name Name of task
     * @param deadline Deadline of the task
     */
    public Deadline(String name, String deadline) {
        super(name, Type.DEADLINE, "(by: " + deadline + ")");
    }
}
