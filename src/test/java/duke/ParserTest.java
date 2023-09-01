package duke;

import instructionstuff.Instruction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;



class ParserTest {

    @Test
    public void parse_byeInstruction_success() {
        try {
            Instruction instruction = Parser.parse("bye sdfsd");
            assertEquals(true, instruction instanceof Instruction.Exit);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parse_invalidIndex_exceptionThrown() {
        try {
            Instruction instruction = Parser.parse("mark a");
            fail();
        } catch (DukeException e) {
            assertEquals("The index is not a valid index. Try again.", e.getMessage());
        }
    }

    @Test
    public void parse_invalidDateFormat_exceptionThrown() {
        try {
            Instruction instruction = Parser.parse("deadline read /by 02/02/2002 18:00");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid date format. Try again.", e.getMessage());
        }
    }

    @Test
    public void parse_unrecognizedInstruction_exceptionThrown() {
        try {
            Instruction instruction = Parser.parse("tada read book");
            fail();
        } catch (DukeException e) {
            assertEquals("Unrecognized instruction. Try again.", e.getMessage());
        }
    }

    @Test
    public void parse_invalidFormat_exceptionThrown() {
        try {
            Instruction instruction = Parser.parse("deadline /to 02-02-2002 1800");
            fail();
        } catch(DukeException e) {
            assertEquals("Invalid format for deadline. Try again.", e.getMessage());
        }
    }

    @Test
    public void parse_eventInstruction_success() {
        try {
            Instruction instruction = Parser.parse("event read /from 02-02-2002 1800 /to 02-02-2002 2034");
            assertEquals(true, instruction instanceof Instruction.Add);
        } catch (DukeException e) {
            fail();
        }
    }
}