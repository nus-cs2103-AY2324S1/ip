package ui;

import main.logic.handler.*;
import ui.inputparser.InstructionType;

import java.util.EnumMap;
import java.util.Map;

public class KniazOutputFlavourer {
    private static final EnumMap<InstructionType, String> INSTRUCT_TO_FLAVOURSTRING =
            new EnumMap<>(Map.of(
                    InstructionType.TODO, "As you say, the ToDo has been created :",
                    InstructionType.DEADLINE, "As you say, the Deadline has been created :",
                    InstructionType.EVENT, "As you say, the Event has been created :",
                    InstructionType.MARK, "As you say, I have marked it as done :",
                    InstructionType.UNMARK, "It is a poor practice to mark it when you are not yet done, but as you say :",
                    InstructionType.LIST, "Time to get to work? Here are all your tasks : ",
                    InstructionType.QUIT, "If that is all for now, then I bid you farewell",
                    InstructionType.DELETE, "It shall be as if this task never existed :",
                    InstructionType.INVALID, "I do not recognise this command."
            ));
    public KniazOutputFlavourer(){

    }

    public String getFlavourFor(InstructionType instr){
        return INSTRUCT_TO_FLAVOURSTRING.getOrDefault(instr, "I do not recognise this command.");
    }

}
