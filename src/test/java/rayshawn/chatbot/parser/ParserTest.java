package rayshawn.chatbot.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static rayshawn.chatbot.messages.Messages.INVALID_COMMAND_FORMAT_MESSAGE;
import static rayshawn.chatbot.messages.Messages.INVALID_TASK_NUMBER_MESSAGE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rayshawn.chatbot.commands.ByeCommand;
import rayshawn.chatbot.commands.Command;
import rayshawn.chatbot.commands.DeadlineCommand;
import rayshawn.chatbot.commands.DeleteCommand;
import rayshawn.chatbot.commands.EventCommand;
import rayshawn.chatbot.commands.HelpCommand;
import rayshawn.chatbot.commands.IncorrectCommand;
import rayshawn.chatbot.commands.ListCommand;
import rayshawn.chatbot.commands.MarkCommand;
import rayshawn.chatbot.commands.NoSuchCommand;
import rayshawn.chatbot.commands.ToDoCommand;
import rayshawn.chatbot.commands.UnmarkCommand;

import java.time.format.DateTimeParseException;

public class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void parse_emptyInput_returnsIncorrect() {
        final String[] emptyInputs = { "", " ", "\n \n"};
        final String resultMessage = String.format(INVALID_COMMAND_FORMAT_MESSAGE, HelpCommand.MESSAGE_USAGE);
        parseAndAssertIncorrectWithMessage(resultMessage, emptyInputs);
    }

    @Test
    public void parse_unknownCommand_returnNoSuchCommand() {
        final String input = "unknowncommandword arguments arguments";
        parseAndAssertCommandType(input, NoSuchCommand.class);
    }

    @Test
    public void parse_helpCommand_ParseCorrectly() {
        final String input = "help";
        parseAndAssertCommandType(input, HelpCommand.class);
    }

    @Test
    public void parse_listCommand_parsedCorrectly() {
        final String input = "list";
        parseAndAssertCommandType(input, ListCommand.class);
    }

    @Test
    public void parse_byeCommand_parsedCorrectly() {
        final String input = "bye";
        parseAndAssertCommandType(input, ByeCommand.class);
    }

    @Test
    public void parse_deleteCommandNoArgs_errorMessage() {
        final String[] inputs = { "delete", "delete " };
        final String resultMessage = String.format(INVALID_COMMAND_FORMAT_MESSAGE, DeleteCommand.MESSAGE_USAGE);
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

    @Test
    public void parse_deleteCommandArgsIsNotSingleNumber_errorMessage() {
        final String[] inputs = { "delete notAnumber ", "delete 8*wh12", "delete 1 2 3 4 5" };
        final String resultMessage = INVALID_TASK_NUMBER_MESSAGE;
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

    @Test
    public void parse_deleteCommandNumericArg_indexParsedCorrectly() {
        final int testIndex = 1;
        final String input = "delete " + testIndex;
        final DeleteCommand result = parseAndAssertCommandType(input, DeleteCommand.class);
        assertEquals(result.getIndex(), testIndex);
    }

    @Test
    public void parse_markCommandNoArgs_errorMessage() {
        final String[] inputs = { "mark", "mark " };
        final String resultMessage = String.format(INVALID_COMMAND_FORMAT_MESSAGE, MarkCommand.MESSAGE_USAGE);
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

    @Test
    public void parse_markCommandArgsIsNotSingleNumber_errorMessage() {
        final String[] inputs = { "mark notAnumber ", "mark 8*wh12", "mark 1 2 3 4 5" };
        final String resultMessage = INVALID_TASK_NUMBER_MESSAGE;
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

    @Test
    public void parse_markCommandNumericArg_indexParsedCorrectly() {
        final int testIndex = 1;
        final String input = "mark " + testIndex;
        final MarkCommand result = parseAndAssertCommandType(input, MarkCommand.class);
        assertEquals(result.getIndex(), testIndex);
    }

    @Test
    public void parse_unmarkCommandNoArgs_errorMessage() {
        final String[] inputs = { "unmark", "unmark " };
        final String resultMessage = String.format(INVALID_COMMAND_FORMAT_MESSAGE, UnmarkCommand.MESSAGE_USAGE);
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

    @Test
    public void parse_unmarkCommandArgsIsNotSingleNumber_errorMessage() {
        final String[] inputs = { "unmark notAnumber ", "unmark 8*wh12", "unmark 1 2 3 4 5" };
        final String resultMessage = INVALID_TASK_NUMBER_MESSAGE;
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

    @Test
    public void parse_unmarkCommandNumericArg_indexParsedCorrectly() {
        final int testIndex = 1;
        final String input = "unmark " + testIndex;
        final UnmarkCommand result = parseAndAssertCommandType(input, UnmarkCommand.class);
        assertEquals(result.getIndex(), testIndex);
    }

    @Test
    public void parse_toDoCommandNoArgs_errorMessage() {
        final String[] inputs = { "todo", "todo " };
        final String resultMessage = String.format(INVALID_COMMAND_FORMAT_MESSAGE, ToDoCommand.MESSAGE_USAGE);
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

    @Test
    public void parse_toDoCommandArg_parsedCorrectly() {
        final String input = "todo homework";
        final String expected_Output = "[T][ ] homework";
        final ToDoCommand result = parseAndAssertCommandType(input, ToDoCommand.class);
        assertEquals(result.toAdd.toString(), expected_Output);
    }

    @Test
    public void parse_deadlineCommandNoArgs_errorMessage() {
        final String[] inputs = { "deadline" , "deadline "};
        final String resultMessage = String.format(INVALID_COMMAND_FORMAT_MESSAGE, DeadlineCommand.MESSAGE_USAGE);
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

    @Test
    public void parse_deadlineCommandNoDescription_errorMessage() {
        final String[] inputs = { "deadline /by 2023-08-30", "deadline /by " };
        final String resultMessage = String.format(INVALID_COMMAND_FORMAT_MESSAGE, DeadlineCommand.MESSAGE_USAGE);
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

    @Test
    public void parse_deadlineCommandNoDate_errorMessage() {
        final String[] inputs = { "deadline homework /by", "deadline homework /by "};
        final String resultMessage = String.format(INVALID_COMMAND_FORMAT_MESSAGE, DeadlineCommand.MESSAGE_USAGE);
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

    @Test
    public void parse_deadlineCommandWrongDateFormats_errorMessage() {
        final String input = "deadline homework /by 30/08/2023";
        final String resultMessage = String.format(INVALID_COMMAND_FORMAT_MESSAGE, DeadlineCommand.MESSAGE_USAGE);
        parseAndAssertIncorrectWithMessage(resultMessage, input);
    }

    @Test
    public void parse_deadlineCommandIncorrectDateArgs_throwsException() {
        final String[] inputs = {
                "deadline homework /by 30 Aug",
                "deadline homework /by 30-08-2023",
                "deadline homework /by notADate"
        };
        for (String input : inputs) {
            parseAndAssertConstructingInvalidDateTimeThrowsException(input);
        }
    }

    @Test
    public void parse_deadlineCommandArg_parsedCorrectly() {
        final String input = "deadline homework /by 2023-08-30";
        final String expected_Output = "[D][ ] homework (by: Aug 30 2023)";
        final DeadlineCommand result = parseAndAssertCommandType(input, DeadlineCommand.class);
        assertEquals(result.toAdd.toString(), expected_Output);
    }

    @Test
    public void parse_eventCommandNoArgs_errorMessage() {
        final String[] inputs = { "event" , "event "};
        final String resultMessage = String.format(INVALID_COMMAND_FORMAT_MESSAGE, EventCommand.MESSAGE_USAGE);
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

    @Test
    public void parse_eventCommandNoDescriptionArg_errorMessage() {
        final String[] inputs = { "event /from 2023-08-30 1PM /to 2PM", "event /by " };
        final String resultMessage = String.format(INVALID_COMMAND_FORMAT_MESSAGE, EventCommand.MESSAGE_USAGE);
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

    @Test
    public void parse_eventCommandNoDateArg_errorMessage() {
        final String[] inputs = { "event party /from", "event party /from "};
        final String resultMessage = String.format(INVALID_COMMAND_FORMAT_MESSAGE, EventCommand.MESSAGE_USAGE);
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

    @Test
    public void parse_eventCommandWrongDateFormats_errorMessage() {
        final String[] inputs = {
                "event party /from 30 Aug",
                "event party /from 30/08/2023",
                "event party /from 30-08-2023",
                "event party /from notADate"
        };
        final String resultMessage = String.format(INVALID_COMMAND_FORMAT_MESSAGE, EventCommand.MESSAGE_USAGE);
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

    @Test
    public void parse_eventCommandMissingTimeArgs_errorMessage() {
        final String[] inputs = {
                "event party /from 2023-08-30",
                "event party /from 2023-08-30 ",
                "event party /from 2023-08-30 1PM /to",
                "event party /from 2023-08-30 1PM /to " ,
        };
        final String resultMessage = String.format(INVALID_COMMAND_FORMAT_MESSAGE, EventCommand.MESSAGE_USAGE);
        parseAndAssertIncorrectWithMessage(resultMessage, inputs);
    }

    @Test
    public void parse_eventCommandIncorrectTimeArgs_throwsException() {
        final String[] inputs = {
                "event party /from 2023-08-30 1.30PM /to 2PM",
                "event party /from 2023-08-30 1300 /to 2PM",
                "event party /from 2023-08-30 13:00 /to 2PM",
                "event party /from 2023-08-30 1PM /to 2.30PM",
                "event party /from 2023-08-30 1PM /to 1400",
                "event party /from 2023-08-30 1PM /to 14:00"
        };
        for (String input : inputs) {
            parseAndAssertConstructingInvalidDateTimeThrowsException(input);
        }
    }

    @Test
    public void parse_eventCommandArg_parsedCorrectly() {
        final String input = "event party /from 2023-08-30 1PM /to 2PM";
        final String expected_Output = "[E][ ] party (from: 2023-08-30 1 PM to: 2 PM)";
        final EventCommand result = parseAndAssertCommandType(input, EventCommand.class);
        assertEquals(result.toAdd.toString(), expected_Output);
    }

    private void parseAndAssertIncorrectWithMessage(String feedbackMessage, String... inputs) {
        for (String input : inputs) {
            final IncorrectCommand result = parseAndAssertCommandType(input, IncorrectCommand.class);
            assertEquals(result.feedbackToUser, feedbackMessage);
        }
    }

    private <T extends Command> T parseAndAssertCommandType(String input, Class<T> expectedCommandClass) {
        final Command result = parser.parseCommand(input);
        assertTrue(result.getClass().isAssignableFrom(expectedCommandClass));
        return (T) result;
    }

    private void parseAndAssertConstructingInvalidDateTimeThrowsException(String input) {
        try {
            parser.parseCommand(input);
        } catch (ArrayIndexOutOfBoundsException a) {
            return;
        } catch (DateTimeParseException d) {
            return;
        } catch (IndexOutOfBoundsException e) {
            return;
        }
        String error = "An event command was successfully created with invalid input: " + input;
        fail(error);
    }
}