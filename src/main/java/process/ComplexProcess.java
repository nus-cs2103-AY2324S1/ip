package process;

/**
 * An interface to define the behavior of a process that can be finished with multiple input
 */
public interface ComplexProcess extends Process {
    /**
     * Returns a string message to instruct the user on the first step in the process
     * @return string message of first set of instructions to user
     */
    public String start();

    /**
     * Getter method to check if process is completed
     * @return whether the process is complete (true) or not complete (false)
     */
    public boolean isComplete();
}
