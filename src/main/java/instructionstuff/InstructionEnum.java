package instructionstuff;

/**
 * An enum class to represent the different Instructions.
 */
public enum InstructionEnum {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo", "T"),
    DEADLINE("deadline", "D"),
    EVENT("event", "E");

    /**
     * An array storing possible string values of the instruction.
     */
    private final String[] VALUES;

    /**
     * Initialises the enums to their respective string values.
     *
     * @param values The array of string value to assign to this enums values array.
     */
    private InstructionEnum(String... values) {
        this.VALUES = values;
    }


    /**
     * Returns the values represented by this enum.
     *
     * @return An array of string of values of this enum.
     */
    private String[] getValues() {
        return this.VALUES;
    }

    /**
     * Returns the enum whose instruction matches the given string.
     *
     * @param s The string to match the instruction against.
     * @return Returns the enum whose value matches the string and null if no such enum.
     */
    public static InstructionEnum getInstructionEnum(String s) {
        for (InstructionEnum i : InstructionEnum.values()) {
            String[] values = i.getValues();
            for (int j = 0; j < values.length; j++) {
                if (values[j].equals(s)) {
                    return i;
                }
            }
        }
        return null;
    }


}
