package duke.assets.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import duke.assets.storage.TaskList;

class SortCommandTest {
    @Test
    void testInvalidCommand() {
        SortCommand command = new SortCommand("invalid command");
        assertEquals(false, command.isValid(new TaskList()));
    }

    @Test
    void testInvalidFlagCommand() {
        SortCommand command = new SortCommand("sort -z");
        assertEquals(false, command.isValid(new TaskList()));
    }

    @Test
    void testValidCommandWithAllFlags() {
        SortCommand command = new SortCommand("sort -a -d");
        assertEquals(true, command.isValid(new TaskList()));
    }
    @Test
    void testValidCommandWithoutDesecndingFlag() {
        SortCommand command = new SortCommand("sort -a");
        assertEquals(true, command.isValid(new TaskList()));
    }
}