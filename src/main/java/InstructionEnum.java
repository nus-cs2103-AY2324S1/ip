/**
 * An enum class to represent the different Instructions.
 */
public enum InstructionEnum {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    /**
     * The string value of the instruction.
     */
    private final String instruction;

    /**
     * Initialises the enums to their respective string values.
     * @param instruction The string value to assign to enums.
     */
    private InstructionEnum(String instruction) {
        this.instruction = instruction;
    }


    /**
     * Returns the instruction of this enum.
     * @return Returns the instruction of this enum.
     */
    private String getInstruction() {
        return this.instruction;
    }

    /**
     * Returns the enum whose instruction matches the given string.
     *
     * @param s The string to match the instruction against.
     * @return Returns the enum whose value matches the string and null if no such enum.
     *
     */
    public static InstructionEnum getInstructionEnum(String s) {
        for (InstructionEnum i : InstructionEnum.values()) {
            if (i.getInstruction().equals(s)) {
                return i;
            }
        }

        return null;
    }


}
