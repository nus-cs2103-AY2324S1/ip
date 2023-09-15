package process;

/**
 * An interface to define the behavior of a process that requires multiple inputs
 */
public interface ComplexProcess extends Process {
    /**
     * Returns a string message to instruct the user on the first step in the process
     * @return string message of first set of instructions to user
     */
    public String firstInstruction();

    /**
     * Getter method to check if process is completed
     * @return whether the process is complete (true) or not complete (false)
     */
    public boolean isComplete();
}
