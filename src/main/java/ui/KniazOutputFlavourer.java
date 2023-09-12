package ui;

import java.util.EnumMap;
import java.util.Map;

import ui.inputparser.InstructionType;

/**
 * Class storing 'flavour' strings for any given InstructionType, where 'flavour' strings are strings printed purely
 * to give the chatbot more personality.
 */
public class KniazOutputFlavourer {
    private static final EnumMap<InstructionType, String> INSTRUCT_TO_FLAVOURSTRING =
            new EnumMap<>(Map.of(
                    InstructionType.TODO,
                    "As you say, the ToDo has been created :",
                    InstructionType.DEADLINE,
                    "As you say, the Deadline has been created :",
                    InstructionType.EVENT,
                    "As you say, the Event has been created :",
                    InstructionType.MARK,
                    "As you say, I have marked it as done :",
                    InstructionType.UNMARK,
                    "It is a poor practice to mark it when you are not yet done, but as you say :",
                    InstructionType.LIST,
                    "Time to get to work? Here are all your tasks : ",
                    InstructionType.QUIT,
                    "If that is all for now, then I bid you farewell",
                    InstructionType.DELETE,
                    "It shall be as if this task never existed :",
                    InstructionType.FIND,
                    "I have searched the records. Here are the results :",
                    InstructionType.INVALID,
                    "I do not recognise this command."
            ));

    static {
        // This ensures that at compile time all instruction types have an explicit, valid handler
        for (InstructionType i : InstructionType.values()) {
            assert(INSTRUCT_TO_FLAVOURSTRING.containsKey(i))
                    : "There was a command " + i.alias + "with no associated flavour string!";
        }
    }

    /**
     * Constructor for the class
     */
    public KniazOutputFlavourer() {

    }

    /**
     * Returns the appropriate flavour string
     * @param instr the instruction to retrieve the flavour string for
     * @return the flavour string
     */
    public String getFlavourFor(InstructionType instr) {
        return INSTRUCT_TO_FLAVOURSTRING.get(instr);
    }

}
