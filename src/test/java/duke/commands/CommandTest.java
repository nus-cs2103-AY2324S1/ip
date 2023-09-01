package duke.commands;

import duke.commands.Command;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {

    @Test
    public void constructorWithTypeOnlyTest() {
        Command command = new Command("bye");
        assertEquals("bye", command.getCommandType());
        assertNull(command.getDescription());
        assertEquals(0, command.getTaskIndex());
        assertNull(command.getDeadlineDate());
        assertNull(command.getEventFromDate());
        assertNull(command.getEventToDate());
    }

    @Test
    public void constructorWithTypeAndDescriptionTest() {
        Command command = new Command("todo", "Sample Todo");
        assertEquals("todo", command.getCommandType());
        assertEquals("Sample Todo", command.getDescription());
        assertEquals(0, command.getTaskIndex());
        assertNull(command.getDeadlineDate());
        assertNull(command.getEventFromDate());
        assertNull(command.getEventToDate());
    }

    @Test
    public void constructorWithTypeAndTaskIndexTest() {
        Command command = new Command("delete", 2);
        assertEquals("delete", command.getCommandType());
        assertNull(command.getDescription());
        assertEquals(2, command.getTaskIndex());
        assertNull(command.getDeadlineDate());
        assertNull(command.getEventFromDate());
        assertNull(command.getEventToDate());
    }

    @Test
    public void constructorWithTypeDescriptionAndDeadlineDateTest() {
        LocalDate deadlineDate = LocalDate.parse("2023-12-31");
        Command command = new Command("deadline", "Sample Deadline", deadlineDate);
        assertEquals("deadline", command.getCommandType());
        assertEquals("Sample Deadline", command.getDescription());
        assertEquals(0, command.getTaskIndex());
        assertEquals(deadlineDate, command.getDeadlineDate());
        assertNull(command.getEventFromDate());
        assertNull(command.getEventToDate());
    }

    @Test
    public void constructorWithTypeDescriptionAndEventDatesTest() {
        LocalDate eventFromDate = LocalDate.parse("2023-12-31");
        LocalDate eventToDate = LocalDate.parse("2024-01-01");
        Command command = new Command("event", "Sample Event", eventFromDate, eventToDate);
        assertEquals("event", command.getCommandType());
        assertEquals("Sample Event", command.getDescription());
        assertEquals(0, command.getTaskIndex());
        assertNull(command.getDeadlineDate());
        assertEquals(eventFromDate, command.getEventFromDate());
        assertEquals(eventToDate, command.getEventToDate());
    }
}
