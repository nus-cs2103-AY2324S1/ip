package duke.service;

import duke.service.CliParserService.ParseResult;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CliParserServiceTest {
    @Test
    public void testParseCommandAndArguments_withValidTodoInput() {
        CliParserService cliParserService = new CliParserService(null, null);
        String addTodoInput = "todo some task"; // "some task" should be in a single string.
        ParseResult expected = new CliParserService.ParseResult("todo", List.of("some task"));
        ParseResult actual = cliParserService.parseCommandAndArguments(addTodoInput);


        assertEquals(expected.getCommandType(), actual.getCommandType());
        assertEquals(expected.getArguments(), actual.getArguments());
    }

    @Test
    public void testParseCommandAndArguments_withValidDeadlineInput() {
        CliParserService cliParserService = new CliParserService(null, null);
        // "some task" should be in a single string, by some date should be in a single string..
        String addTodoInput = "deadline some task /by some date";

        ParseResult expected = new CliParserService.ParseResult(
                "deadline",
                List.of("some task", "by some date")
        );
        ParseResult actual = cliParserService.parseCommandAndArguments(addTodoInput);

        assertEquals(expected.getCommandType(), actual.getCommandType());
        assertEquals(expected.getArguments(), actual.getArguments());
    }

    @Test
    public void testParseCommandAndArguments_withValidEventInput() {
        CliParserService cliParserService = new CliParserService(null, null);
        // "some task" should be in a single string, by some date should be in a single string..
        String addTodoInput = "event some task /from some date /to another date";

        ParseResult expected = new CliParserService.ParseResult(
                "event",
                List.of("some task", "from some date", "to another date")
        );
        ParseResult actual = cliParserService.parseCommandAndArguments(addTodoInput);

        assertEquals(expected.getCommandType(), actual.getCommandType());
        assertEquals(expected.getArguments(), actual.getArguments());
    }

    @Test
    public void testParseCommandAndArguments_withExtraSpaces_shouldExtractCommandType() {
        CliParserService cliParserService = new CliParserService(null, null);
        // "some task" should be in a single string, by some date should be in a single string..
        String addTodoInput = "  mark    some    weird input :>  ";

        ParseResult expected = new CliParserService.ParseResult(
                "mark",
                List.of("some    weird input :>")
        );
        ParseResult actual = cliParserService.parseCommandAndArguments(addTodoInput);

        assertEquals(expected.getCommandType(), actual.getCommandType());
        assertEquals(expected.getArguments(), actual.getArguments());
    }

    @Test
    public void testParseCommandAndArguments_withNoArguments() {
        CliParserService cliParserService = new CliParserService(null, null);
        // "some task" should be in a single string, by some date should be in a single string..
        String addTodoInput = "list";

        ParseResult expected = new CliParserService.ParseResult(
                "list",
                List.of()
        );
        ParseResult actual = cliParserService.parseCommandAndArguments(addTodoInput);

        assertEquals(expected.getCommandType(), actual.getCommandType());
        assertEquals(expected.getArguments(), actual.getArguments());
    }

    @Test
    public void testParseCommandAndArguments_withExtraSlashes() {
        CliParserService cliParserService = new CliParserService(null, null);
        // "some task" should be in a single string, by some date should be in a single string..
        String addTodoInput = "event some event /from today /to tomorrow /extra spaces";

        ParseResult expected = new CliParserService.ParseResult(
                "event",
                List.of("some event", "from today", "to tomorrow", "extra spaces")
        );
        ParseResult actual = cliParserService.parseCommandAndArguments(addTodoInput);

        assertEquals(expected.getCommandType(), actual.getCommandType());
        assertEquals(expected.getArguments(), actual.getArguments());
    }

    @Test
    public void testParseCommandAndArguments_withOnlySlashes() {
        CliParserService cliParserService = new CliParserService(null, null);
        // "some task" should be in a single string, by some date should be in a single string..
        String addTodoInput = "/from today /to tomorrow";

        ParseResult expected = new CliParserService.ParseResult(
                "/from",
                List.of("today", "to tomorrow")
        );
        ParseResult actual = cliParserService.parseCommandAndArguments(addTodoInput);

        assertEquals(expected.getCommandType(), actual.getCommandType());
        assertEquals(expected.getArguments(), actual.getArguments());
    }

    @Test
    public void testParseCommandAndArguments_withInconsistentCases() {
        CliParserService cliParserService = new CliParserService(null, null);
        // "some task" should be in a single string, by some date should be in a single string..
        String addTodoInput = "DeaDLine SoME deADLine /by tODAy";

        ParseResult expected = new CliParserService.ParseResult(
                "deadline",
                List.of("SoME deADLine", "by tODAy")
        );
        ParseResult actual = cliParserService.parseCommandAndArguments(addTodoInput);

        assertEquals(expected.getCommandType(), actual.getCommandType());
        assertEquals(expected.getArguments(), actual.getArguments());
    }

    @Test
    public void testParseCommandAndArguments_withSpecialCharacters() {
        CliParserService cliParserService = new CliParserService(null, null);
        // "some task" should be in a single string, by some date should be in a single string..
        String addTodoInput = "todo task@#!$%^&*()";

        ParseResult expected = new CliParserService.ParseResult(
                "todo",
                List.of("task@#!$%^&*()")
        );
        ParseResult actual = cliParserService.parseCommandAndArguments(addTodoInput);

        assertEquals(expected.getCommandType(), actual.getCommandType());
        assertEquals(expected.getArguments(), actual.getArguments());
    }
}
