package duke.parser;

import duke.command.*;

public class Parser {
    public static Command parse(String instr) {
        String keyWord = instr.split(" ")[0];
        switch (keyWord) {
        case"":
            return new Unknown("☹ OOPS!!! You said nothing!\n");
        case "deadline":
            return new AddDeadline(instr.substring(8));
        case "event":
            return new AddEvent(instr.substring(5));
        case "todo":
            return new AddTodo(instr.substring(4));
        case "mark":
            return new Remark(instr.substring(4),1);
        case "unmark":
            return new Remark(instr.substring(6), 0);
        case "delete":
            return new Delete(instr.substring(6));
        case "list":
            return new List(instr);
        case "bye":
            return new Bye(instr);
        default:
            return new Unknown("☹ OOPS!!! I can't understand.\n");
        }
    }
}
