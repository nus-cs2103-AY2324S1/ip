package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class CommandTest {

    @Test
    public void constructorWithTypeOnlyTest() {
        Command command = Command.of("bye");
        assertEquals("bye", command.getCommandType());
        assertNull(command.getDescription());
        assertEquals(0, command.getTaskIndex());
        assertNull(command.getDeadlineDate());
        assertNull(command.getEventFromDate());
        assertNull(command.getEventToDate());
    }

    @Test
    public void constructorWithTypeAndDescriptionTest() {
        Command command = Command.ofDescription("todo", "Sample Todo");
        assertEquals("todo", command.getCommandType());
        assertEquals("Sample Todo", command.getDescription());
        assertEquals(0, command.getTaskIndex());
        assertNull(command.getDeadlineDate());
        assertNull(command.getEventFromDate());
        assertNull(command.getEventToDate());
    }

    @Test
    public void constructorWithTypeAndTaskIndexTest() {
        Command command = Command.ofTaskIndex("delete", 2);
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
        Command command = Command.ofDeadline("deadline", "Sample Deadline", deadlineDate);
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
        Command command = Command.ofEvent("event", "Sample Event", eventFromDate, eventToDate);
        assertEquals("event", command.getCommandType());
        assertEquals("Sample Event", command.getDescription());
        assertEquals(0, command.getTaskIndex());
        assertNull(command.getDeadlineDate());
        assertEquals(eventFromDate, command.getEventFromDate());
        assertEquals(eventToDate, command.getEventToDate());
    }
}
