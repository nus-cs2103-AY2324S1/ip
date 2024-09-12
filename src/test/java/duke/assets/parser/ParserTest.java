package duke.assets.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import duke.assets.storage.TaskList;
import duke.dukeexceptions.InvalidCommandException;

class ParserTest {
    @Test
    void invalidCommandTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "invalidCommand";
        String expected = "Please input a valid command.";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }
    @Test
    void validTodoTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "todo dance";
        String expected = "No problem! Just remember to do your task before the deadline.";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }

    @Test
    void todoMissingInformationTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "todo";
        String expected = "Please provide a description about your task.";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }

    @Test
    void deadlineMissingInformationTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "deadline /by 2023-09-20";
        String expected = "Please include description about the task you would like to add.";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }

    @Test
    void deadlineMissingDateTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "deadline sleep";
        String expected = "Please include the deadline date of your task after \"/by\" command.";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }

    @Test
    void deadlineInvalidTimeFormatTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "deadline sleep /by 2023-09-23 9999";
        String expected = "Please ensure your date and time are valid";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }

    @Test
    void deadlineInvalidDateFormatTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "deadline sleep /by 2023-09-2300 2359";
        String expected = "Please ensure your date and time are valid";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }

    @Test
    void eventMissingInformationTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "event /from 2023-09-23 2359 /to 2023-09-24 2359";
        String expected = "Please include information about the task you would like to add.";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }

    @Test
    void eventInvalidStartTimeFormatTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "event sleep /from 2023-09-23 9999 /to 2023-09-24 2359";
        String expected = "Please input a valid date and time";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }

    @Test
    void eventInvalidEndTimeFormatTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "event sleep /from 2023-09-23 2359 /to 2023-09-24 9999";
        String expected = "Please input a valid date and time";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }

    @Test
    void eventInvalidStartDateFormatTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "event sleep /from 2023-09-2300 2359 /to 2023-09-24 2359";
        String expected = "Please input a valid date and time";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }

    @Test
    void eventInvalidEndDateFormatTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "event sleep /from 2023-09-23 2359 /to 2023-09-2400 2359";
        String expected = "Please input a valid date and time";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }

    @Test
    void eventMissingStartDateTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "event sleep /to 2023-09-24 2359";
        String expected = "Please verify you have included the start date after /from and "
                + "end date after /to commands";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }

    @Test
    void eventMissingEndDateTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "event sleep /from 2023-09-23 2359";
        String expected = "Please verify you have included the start date after /from and "
                + "end date after /to commands";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }

    @Test
    void markEmptyListTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "mark 1";
        String expected = "Please add at least one task to your list first :)";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }

    @Test
    void unmarkEmptyListTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "unmark 1";
        String expected = "Please add at least one task to your list first.";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }

    @Test
    void deleteEmptyListTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "delete 1";
        String expected = "Can't delete from an empty list :(";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }

    @Test
    void validSortCommandTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "sort -a -d";
        String expected = "Done! I have sorted your list in descending alphabetical order!";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }

    @Test
    void invalidSortByFlagTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "sort -s";
        String expected = "Only sorting by alphabetical order or chronological order supported. Use flags"
                + " -a for alphabetical and -c for chronological";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }

    @Test
    void invalidSortDescendingFlagTest() {
        Parser parser = new Parser();
        TaskList tasklist = new TaskList();
        String command = "sort -a -s";
        String expected = "Please check if you have a typo in your command. If you want to sort in descending order, "
                + "use the flag -d";
        assertEquals(expected, parser.passUserCommand(command, tasklist));
    }
}
