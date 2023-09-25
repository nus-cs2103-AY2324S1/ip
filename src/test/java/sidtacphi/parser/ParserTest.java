package sidtacphi.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import sidtacphi.contact.ContactList;
import sidtacphi.exception.SidException;
import sidtacphi.task.TaskList;

public class ParserTest {
    @Test
    public void testInvalidCommand() {
        assertThrows(SidException.class, 
                () -> Parser.parseInput(new TaskList(), new ContactList(), "Wow I love JUnit Tests"));
    }
}
