package parser;

import java.util.List;
import java.util.Arrays;

/**
 * Class encapsulating a command given to Kniaz, should only be instantiated via KniazParser when it parses a command
 */
public class KniazCommand {

    /**
     * The type of instruction this command contains -- Such as list, event, bye, etc.
     */
    private KniazParser.InstructionType instruction;

    /**
     * the arguments given to this command
     */
    private List<String> args;

    /**
     * Protected constructor -- This shouldn't be instantiated from areas outside the parser package
     * @param instruction the type of instruction this command contains
     * @param args the arguments to the command
     */
    protected KniazCommand(KniazParser.InstructionType instruction, String... args) {
        this.instruction = instruction;
        this.args = Arrays.asList(args);
    }

    /**
     * whether this has a certain instruction
     * @param compareTo the instruction to comapre against
     * @return whether this command has the supplied instruction
     */
    public boolean instructionEquals(KniazParser.InstructionType compareTo) {
        return this.instruction.equals(compareTo);
    }

    /**
     * Getter method for the arguments in this command
     * @return the arguments in this command, in list form
     */
    public List<String> getArgs() {
        return args;
    }
}
