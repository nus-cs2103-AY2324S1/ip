package duke.assets.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import duke.assets.storage.TaskList;
class ParserTest {
    @Test
    void addTaskReturnStringTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "todo dance";
        String expected = "No problem! Just remember to do your task before the deadline.";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }
    @Test
    void missingTaskInformationTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "todo";
        String expected = "Please provide a description about your task.";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }
    @Test
    void taskNotPresentTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "unmark 1";
        String expected = "Please add at least one task to your list first.";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }
    @Test
    void invalidSortCommand() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "sort -a -d";
        String expected = "Done! I have sorted your list in descending alphabetical order!";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }
}
