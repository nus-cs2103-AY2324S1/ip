package ui;

/**
 * Class encapsulating a set of instructions to the GUI
 */
public class UiCommand {
    //TODO : Think of a nice way to encapsulate something besides data in here
    /**
     * The types of instructions there are.
     */
    public enum UiInstructType {
        PRINT,
        QUIT
    }

    private UiInstructType instructType;
    private String arg;

    /**
     * The constructor for this class
     * @param instructType the type of command this is
     * @param arg the arguments to this command (like what to print to the PRINT command)
     */
    public UiCommand(UiInstructType instructType, String arg) {
        this.instructType = instructType;
        this.arg = arg;
    }

    /**
     * Getter for the instruction type
     * @return the instruction type of this command
     */
    public UiInstructType getInstructType() {
        return instructType;
    }

    /**
     * Getter for the args to this command
     * @return the arg to this command
     */
    public String getArg() {
        return arg;
    }



}
