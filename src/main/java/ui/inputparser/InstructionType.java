package ui.inputparser;

import ui.inputparser.commands.KniazCommand;

import java.util.regex.Pattern;

/**
 * The types of instructions that Kniaz can accept
 * Each member of this enum has an alias, that represents the command typed into Kniaz
 * E.g. the DEADLINE InstructionType is related to the command "deadline".
 */
public enum InstructionType {
    TODO("todo", 0, ""),
    DEADLINE("deadline", 1, "/by.*"),
    EVENT("event", 2, "/(from|to).*"),
    MARK("mark", 1, "\\d+"),
    UNMARK("unmark", 1, "\\d+"),
    LIST("list", 0, ""),
    QUIT("bye", 0 , ""),
    DELETE("delete", 1, "\\d+"),
    INVALID("", 0 , ""); // placeholder for anything not recognised

    public final String alias;
    public final int numArgs;


    public final Pattern argPattern;



    private InstructionType(String alias, int numArgs, String argRegex) {
        this.alias = alias;
        this.numArgs = numArgs;
        this.argPattern = Pattern.compile(argRegex);
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
