package parser;

import java.util.List;
import java.util.Arrays;
public class KniazCommand {

    private KniazParser.InstructionType instruction;
    private List<String> args;

    protected KniazCommand(KniazParser.InstructionType instruction, String... args) {
        this.instruction = instruction;
        this.args = Arrays.asList(args);
    }

    public boolean instructionEquals(KniazParser.InstructionType compareTo) {
        return this.instruction.equals(compareTo);
    }

    public List<String> getArgs() {
        return args;
    }
}
