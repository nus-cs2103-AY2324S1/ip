package taskmate.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import taskmate.exceptions.InvalidCommandTypeException;



public class ParserTest {

    @Test
    public void getCommandType_normalCommands_success() {

        String deadlineCommand = "deadline this is some long task by the user /by 2021-01-01";
        try {
            String parsedCommandType = Parser.getCommandType(deadlineCommand);
            assertEquals(parsedCommandType, "deadline");
        } catch (InvalidCommandTypeException e) {
            fail();
        }

        String deleteCommand = "delete 10243";
        try {
            String parsedCommandType = Parser.getCommandType(deleteCommand);
            assertEquals(parsedCommandType, "delete");
        } catch (InvalidCommandTypeException e) {
            fail();
        }

        String eventCommand = "event another event task /from 1999-12-01 /to 2035-06-30";
        try {
            String parsedCommandType = Parser.getCommandType(eventCommand);
            assertEquals(parsedCommandType, "event");
        } catch (InvalidCommandTypeException e) {
            fail();
        }

        String exitCommand = "bye";
        try {
            String parsedCommandType = Parser.getCommandType(exitCommand);
            assertEquals(parsedCommandType, "bye");
        } catch (InvalidCommandTypeException e) {
            fail();
        }

        String findCommand = "find 243";
        try {
            String parsedCommandType = Parser.getCommandType(findCommand);
            assertEquals(parsedCommandType, "find");
        } catch (InvalidCommandTypeException e) {
            fail();
        }

        String helpCommand = "help";
        try {
            String parsedCommandType = Parser.getCommandType(helpCommand);
            assertEquals(parsedCommandType, "help");
        } catch (InvalidCommandTypeException e) {
            fail();
        }

        String listCommand = "list";
        try {
            String parsedCommandType = Parser.getCommandType(listCommand);
            assertEquals(parsedCommandType, "list");
        } catch (InvalidCommandTypeException e) {
            fail();
        }

        String markCommand = "mark 90";
        try {
            String parsedCommandType = Parser.getCommandType(markCommand);
            assertEquals(parsedCommandType, "mark");
        } catch (InvalidCommandTypeException e) {
            fail();
        }

        String todoCommand = "todo go to buy groceries";
        try {
            String parsedCommandType = Parser.getCommandType(todoCommand);
            assertEquals(parsedCommandType, "todo");
        } catch (InvalidCommandTypeException e) {
            fail();
        }

        String unmarkCommand = "unmark 90";
        try {
            String parsedCommandType = Parser.getCommandType(unmarkCommand);
            assertEquals(parsedCommandType, "unmark");
        } catch (InvalidCommandTypeException e) {
            fail();
        }
    }

    @Test
    public void getCommandType_normalCommandsWithTrailingWhitespaces_success() {

        String deadlineCommand = " deadline this is some long task by the user /by 2021-01-01";
        try {
            String parsedCommandType = Parser.getCommandType(deadlineCommand);
            assertEquals(parsedCommandType, "deadline");
        } catch (InvalidCommandTypeException e) {
            fail();
        }

        String deleteCommand = " delete 10243";
        try {
            String parsedCommandType = Parser.getCommandType(deleteCommand);
            assertEquals(parsedCommandType, "delete");
        } catch (InvalidCommandTypeException e) {
            fail();
        }

        String eventCommand = " event another event task /from 1999-12-01 /to 2035-06-30";
        try {
            String parsedCommandType = Parser.getCommandType(eventCommand);
            assertEquals(parsedCommandType, "event");
        } catch (InvalidCommandTypeException e) {
            fail();
        }

        String exitCommand = " bye ";
        try {
            String parsedCommandType = Parser.getCommandType(exitCommand);
            assertEquals(parsedCommandType, "bye");
        } catch (InvalidCommandTypeException e) {
            fail();
        }

        String findCommand = " find 243";
        try {
            String parsedCommandType = Parser.getCommandType(findCommand);
            assertEquals(parsedCommandType, "find");
        } catch (InvalidCommandTypeException e) {
            fail();
        }

        String helpCommand = " help ";
        try {
            String parsedCommandType = Parser.getCommandType(helpCommand);
            assertEquals(parsedCommandType, "help");
        } catch (InvalidCommandTypeException e) {
            fail();
        }

        String listCommand = " list ";
        try {
            String parsedCommandType = Parser.getCommandType(listCommand);
            assertEquals(parsedCommandType, "list");
        } catch (InvalidCommandTypeException e) {
            fail();
        }

        String markCommand = " mark 90";
        try {
            String parsedCommandType = Parser.getCommandType(markCommand);
            assertEquals(parsedCommandType, "mark");
        } catch (InvalidCommandTypeException e) {
            fail();
        }

        String todoCommand = " todo go to buy groceries";
        try {
            String parsedCommandType = Parser.getCommandType(todoCommand);
            assertEquals(parsedCommandType, "todo");
        } catch (InvalidCommandTypeException e) {
            fail();
        }

        String unmarkCommand = " unmark 90";
        try {
            String parsedCommandType = Parser.getCommandType(unmarkCommand);
            assertEquals(parsedCommandType, "unmark");
        } catch (InvalidCommandTypeException e) {
            fail();
        }
    }


    @Test
    public void getCommandType_unrecognizedCommand_exceptionThrown() {
        String invalidCommand = "goodbye";
        try {
            Parser.getCommandType(invalidCommand);
            fail();
        } catch (InvalidCommandTypeException e) {
            assertNull(e.getMessage());
        }

        String typoCommand = "umark";
        try {
            Parser.getCommandType(typoCommand);
            fail();
        } catch (InvalidCommandTypeException e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void getCommandType_emptyCommand_exceptionThrown() {
        String emptyCommand = "";
        try {
            Parser.getCommandType(emptyCommand);
            fail();
        } catch (InvalidCommandTypeException e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void getCommandType_correctCommandButTypo_success() {
        String typoUpdateCommand = "update1";
        String parsedCommand;
        try {
            parsedCommand = Parser.getCommandType(typoUpdateCommand);
            assertEquals(parsedCommand, "update");
        } catch (InvalidCommandTypeException e) {
            assertNull(e.getMessage());
            fail();
        }

        String typoTodoCommand = "todoread book";
        try {
            parsedCommand = Parser.getCommandType(typoTodoCommand);
            assertEquals(parsedCommand, "todo");
        } catch (InvalidCommandTypeException e) {
            assertNull(e.getMessage());
            fail();
        }

        String typoDeadlineCommand = "deadlineread book";
        try {
            parsedCommand = Parser.getCommandType(typoDeadlineCommand);
            assertEquals(parsedCommand, "deadline");
        } catch (InvalidCommandTypeException e) {
            assertNull(e.getMessage());
            fail();
        }

        String typoEventCommand = "event";
        try {
            parsedCommand = Parser.getCommandType(typoEventCommand);
            assertEquals(parsedCommand, "event");
        } catch (InvalidCommandTypeException e) {
            assertNull(e.getMessage());
            fail();
        }

        String typoListCommand = "list tasks";
        try {
            parsedCommand = Parser.getCommandType(typoListCommand);
            assertEquals(parsedCommand, "list");
        } catch (InvalidCommandTypeException e) {
            assertNull(e.getMessage());
            fail();
        }

        String typoFindCommand = "findquery";
        try {
            parsedCommand = Parser.getCommandType(typoFindCommand);
            assertEquals(parsedCommand, "find");
        } catch (InvalidCommandTypeException e) {
            assertNull(e.getMessage());
            fail();
        }

        String typoMarkCommand = "mark1";
        try {
            parsedCommand = Parser.getCommandType(typoMarkCommand);
            assertEquals(parsedCommand, "mark");
        } catch (InvalidCommandTypeException e) {
            assertNull(e.getMessage());
            fail();
        }

        String typoUnmarkCommand = "unmark201384792378141";
        try {
            parsedCommand = Parser.getCommandType(typoUnmarkCommand);
            assertEquals(parsedCommand, "unmark");
        } catch (InvalidCommandTypeException e) {
            assertNull(e.getMessage());
            fail();
        }

        String typoHelpCommand = "help me";
        try {
            parsedCommand = Parser.getCommandType(typoHelpCommand);
            assertEquals(parsedCommand, "help");
        } catch (InvalidCommandTypeException e) {
            assertNull(e.getMessage());
            fail();
        }

        String typoByeCommand = "byebye";
        try {
            parsedCommand = Parser.getCommandType(typoByeCommand);
            assertEquals(parsedCommand, "bye");
        } catch (InvalidCommandTypeException e) {
            assertNull(e.getMessage());
            fail();
        }
    }

    @Test
    public void getCommandType_correctCommandButEmptyDescription_success() {
        String typoUpdateCommand = "update";
        String parsedCommand;
        try {
            parsedCommand = Parser.getCommandType(typoUpdateCommand);
            assertEquals(parsedCommand, "update");
        } catch (InvalidCommandTypeException e) {
            assertNull(e.getMessage());
            fail();
        }

        String typoTodoCommand = "todo";
        try {
            parsedCommand = Parser.getCommandType(typoTodoCommand);
            assertEquals(parsedCommand, "todo");
        } catch (InvalidCommandTypeException e) {
            assertNull(e.getMessage());
            fail();
        }

        String typoDeadlineCommand = "deadline";
        try {
            parsedCommand = Parser.getCommandType(typoDeadlineCommand);
            assertEquals(parsedCommand, "deadline");
        } catch (InvalidCommandTypeException e) {
            assertNull(e.getMessage());
            fail();
        }

        String typoEventCommand = "event";
        try {
            parsedCommand = Parser.getCommandType(typoEventCommand);
            assertEquals(parsedCommand, "event");
        } catch (InvalidCommandTypeException e) {
            assertNull(e.getMessage());
            fail();
        }

        String typoFindCommand = "find";
        try {
            parsedCommand = Parser.getCommandType(typoFindCommand);
            assertEquals(parsedCommand, "find");
        } catch (InvalidCommandTypeException e) {
            assertNull(e.getMessage());
            fail();
        }

        String typoMarkCommand = "mark";
        try {
            parsedCommand = Parser.getCommandType(typoMarkCommand);
            assertEquals(parsedCommand, "mark");
        } catch (InvalidCommandTypeException e) {
            assertNull(e.getMessage());
            fail();
        }

        String typoUnmarkCommand = "unmark";
        try {
            parsedCommand = Parser.getCommandType(typoUnmarkCommand);
            assertEquals(parsedCommand, "unmark");
        } catch (InvalidCommandTypeException e) {
            assertNull(e.getMessage());
            fail();
        }
    }

}
