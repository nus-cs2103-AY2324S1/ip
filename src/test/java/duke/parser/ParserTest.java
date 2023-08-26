package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parser_validCommand_success() {
        String[] validCommands = {"bye", "mark", "unmark", "list", "todo", "deadline", "event", "delete"};
        for (String vc : validCommands) {
            assertEquals(Parser.Command.valueOf(vc.toUpperCase()), new Parser(vc).getCommand());
        }
    }

    @Test
    public void parser_invalidCommand_success() {
        String[] invalidCommands = {"bai", "asdfghjkl"};
        for (String ic : invalidCommands) {
            assertEquals(Parser.Command.INVALID, new Parser(ic).getCommand());
        }
    }

    @Test
    public void parser_parseInput_success() {
        String[] commands = {
            "bye",
            "mark 1",
            "unmark 1",
            "list",
            "todo todo",
            "deadline deadline /by 2023-08-25 23:59",
            "event event /from 2023-08-26 23:59 /to 2023-08-27 23:59",
            "delete 1"
        };
        for (String c : commands) {
            Parser parsed = new Parser(c);
            assertEquals(Parser.Command.valueOf(c.split(" ")[0].toUpperCase()), parsed.getCommand());
            assertEquals(!c.contains(" ") ? "" : c.substring(c.indexOf(" ") + 1),
                    parsed.getArgument());
        }
    }
}
