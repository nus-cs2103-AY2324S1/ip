package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



class ParserTest {
    @Test
    @DisplayName("Test assert ParserException in parseMark (when invalid Task number passed)")
    public void parseMark_invalidTaskNo_throwsParserException() {
        Exception parserException = assertThrows(duke.ParserException.class, ()
                -> new Parser().parseMark("mark 6", new TaskList()));
        assertEquals("Please enter valid Task No. to mark!", parserException.getMessage());
    }
    @Test
    @DisplayName("Test assert ParserException in parseMark (when non-int argument passed)")
    public void parseMark_nonIntArgument_throwsParserException() {
        Exception parserException = assertThrows(duke.ParserException.class, ()
                -> new Parser().parseMark("mark f", new TaskList()));
        assertEquals("Please enter valid Task No. (INTEGER) to mark in the format: 'mark 4'",
                parserException.getMessage());
    }

    @Test
    @DisplayName("Test assert ParserException in parseMark (when non-int argument passed)")
    public void parseDelete_nonIntArgument_throwsParserException() {
        Exception parserException = assertThrows(duke.ParserException.class, ()
                -> new Parser().parseDelete("delete f", new TaskList()));
        assertEquals("Please enter valid Task No. (INTEGER) to delete in the format: 'delete 4'",
                parserException.getMessage());
    }
}
