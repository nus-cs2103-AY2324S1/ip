package ui.inputparser;

/**
 * The types of instructions that Kniaz can accept
 * Each member of this enum has an alias, that represents the command typed into Kniaz
 * E.g. the DEADLINE InstructionType is related to the command "deadline".
 */
public enum InstructionType {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    MARK("mark"),
    UNMARK("unmark"),
    LIST("list"),
    QUIT("bye"),
    DELETE("delete"),
    INVALID(""); // placeholder for anything not recognised

    public final String alias;

    private InstructionType(String alias) {
        this.alias = alias;
    }

    /**
     * Converts an alias as a string to an instruction type,
     * returns an INVALID type if not recognised or is empty string
     * @param alias the alias of the instruction type to convert to
     * @return the instruction corresponding to the alias
     */
    public static InstructionType stringToInstrType(String alias) {
        InstructionType output = INVALID;

        for (InstructionType instr : values()) {
            if (instr.alias.equals(alias)) {
                output = instr;
            }
        }

        return output;
    }


}
