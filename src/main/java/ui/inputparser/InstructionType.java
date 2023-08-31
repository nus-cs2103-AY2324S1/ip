package ui.inputparser;

/**
 * The types of instructions that Kniaz can accept
 * Each member of this enum has an alias, that represents the command typed into Kniaz
 * E.g. the DEADLINE InstructionType is related to the command "deadline".
 */
public enum InstructionType {
    TODO("todo", 1, 0, new String[]{}),
    DEADLINE("deadline", 1, 1, new String[]{"by"}),
    EVENT("event", 1, 2, new String[]{"from","to"}),
    MARK("mark", 1, 0, new String[]{}),
    UNMARK("unmark", 1, 0, new String[]{}),
    LIST("list", 0, 0, new String[]{}),
    QUIT("bye", 0 , 0, new String[]{}),
    DELETE("delete", 1, 0, new String[]{}),
    FIND("find",1,0,new String[]{}),
    INVALID("", 0 , 0, new String[]{}); // placeholder for anything not recognised

    public final String alias;
    public final int numUnnamedArgs;

    public final int numNamedArgs;

    public final String[] argNames;


    public static final String[] EMPTY = new String[]{};

    private InstructionType(String alias, int numUnnamedArgs, int namedArgs, String[] argNames)
            throws ExceptionInInitializerError {
        this.alias = alias;
        this.numUnnamedArgs = numUnnamedArgs;
        this.numNamedArgs = namedArgs;
        this.argNames = argNames;
        if (this.numNamedArgs != argNames.length){
            throw new ExceptionInInitializerError(String.format(
                    "InstructionType should have %s named args specified, " +
                    "but only has %s!", namedArgs, argNames.length));
        }
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
