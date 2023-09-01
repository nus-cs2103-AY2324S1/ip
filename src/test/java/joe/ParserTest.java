package joe;

import static org.junit.jupiter.api.Assertions.assertTrue;

import joe.commands.ByeCommand;
import joe.commands.Command;
import joe.commands.DeadlineCommand;
import joe.commands.DeleteCommand;
import joe.commands.EventCommand;
import joe.commands.InvalidCommand;
import joe.commands.ListCommand;
import joe.commands.MarkCommand;
import joe.commands.TodoCommand;
import joe.commands.UnmarkCommand;
import org.junit.jupiter.api.Test;

public class ParserTest {

  // Tests for empty commands
  @Test
  public void parse_empty_invalidCommand() {
    Command c = Parser.parse("");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_whitespace_invalidCommand() {
    Command c = Parser.parse("    ");
    assertTrue(c instanceof InvalidCommand);
  }

  // Tests for invalid command words
  @Test
  public void parse_randomOneWordInput_invalidCommand() {
    Command c = Parser.parse("sdfjhg");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_randomManyWordInput_invalidCommand() {
    Command c = Parser.parse("sdfjhg aklg1h la1kg jhalk1 fhalk 12321");
    assertTrue(c instanceof InvalidCommand);
  }

  // Tests for extra whitespaces in args
  @Test
  public void parse_markWithExtraWhitespace_success() {
    Command c = Parser.parse("mark 1   ");
    assertTrue(c instanceof MarkCommand);
    Command c1 = Parser.parse("mark     1");
    assertTrue(c1 instanceof MarkCommand);
  }

  @Test
  public void parse_unmarkWithExtraWhitespace_success() {
    Command c = Parser.parse("unmark 1   ");
    assertTrue(c instanceof UnmarkCommand);
    Command c1 = Parser.parse("unmark     1");
    assertTrue(c1 instanceof UnmarkCommand);
  }

  @Test
  public void parse_todoWithExtraWhitespace_success() {
    Command c = Parser.parse("todo wow whitespaces   ");
    assertTrue(c instanceof TodoCommand);
    Command c1 = Parser.parse("todo     wow whitespaces");
    assertTrue(c1 instanceof TodoCommand);
  }

  @Test
  public void parse_deadlineWithExtraWhitespace_success() {
    Command c = Parser.parse("deadline wow whitespaces /by 31/08/2023 1350   ");
    assertTrue(c instanceof DeadlineCommand);
    Command c1 = Parser.parse("deadline     wow whitespaces /by 31/08/2023 1350");
    assertTrue(c1 instanceof DeadlineCommand);
  }

  @Test
  public void parse_eventWithExtraWhitespace_success() {
    Command c = Parser.parse("event wow whitespaces /from 31/08/2023 1350 /to 31/08/2023 1351   ");
    assertTrue(c instanceof EventCommand);
    Command c1 =
        Parser.parse("event     wow whitespaces /from 31/08/2023 1350 /to 31/08/2023 1351");
    assertTrue(c1 instanceof EventCommand);
  }

  // Tests for one word commands
  @Test
  public void parse_expectedBye_success() {
    Command c = Parser.parse("bye");
    assertTrue(c instanceof ByeCommand);
  }

  @Test
  public void parse_expectedList_success() {
    Command c = Parser.parse("list");
    assertTrue(c instanceof ListCommand);
  }

  @Test
  public void parse_uppercaseValidCommand_invalidCommand() {
    Command c = Parser.parse("LIST");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_listWithArgs_invalidCommand() {
    Command c = Parser.parse("list 1");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_byeWithArgs_invalidCommand() {
    Command c = Parser.parse("bye 1");
    assertTrue(c instanceof InvalidCommand);
  }

  // Tests for mark
  @Test
  public void parse_markNoArgs_invalidCommand() {
    Command c = Parser.parse("mark");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_markManyArgs_invalidCommand() {
    Command c = Parser.parse("mark 1 1");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_markNonNumericalArgs_invalidCommand() {
    Command c = Parser.parse("mark a");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_markExpected_success() {
    Command c = Parser.parse("mark 1");
    assertTrue(c instanceof MarkCommand);
  }

  // Tests for unmark
  @Test
  public void parse_unmarkNoArgs_invalidCommand() {
    Command c = Parser.parse("unmark");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_unmarkManyArgs_invalidCommand() {
    Command c = Parser.parse("unmark 1 1");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_unmarkNonNumericalArgs_invalidCommand() {
    Command c = Parser.parse("unmark a");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_unmarkExpected_success() {
    Command c = Parser.parse("unmark 1");
    assertTrue(c instanceof UnmarkCommand);
  }

  // Tests for delete
  @Test
  public void parse_deleteNoArgs_invalidCommand() {
    Command c = Parser.parse("delete");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_deleteManyArgs_invalidCommand() {
    Command c = Parser.parse("delete 1 1");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_deleteNonNumericalArgs_invalidCommand() {
    Command c = Parser.parse("delete a");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_deleteExpected_success() {
    Command c = Parser.parse("delete 1");
    assertTrue(c instanceof DeleteCommand);
  }

  // Tests for todo
  @Test
  public void parse_todoNoArgs_invalidCommand() {
    Command c = Parser.parse("todo");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_todoWhitespaceArgs_invalidCommand() {
    Command c = Parser.parse("todo       ");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_todoExpected_success() {
    Command c = Parser.parse("todo task");
    assertTrue(c instanceof TodoCommand);
  }

  @Test
  public void parse_todoExpected1_success() {
    Command c = Parser.parse("todo 1 egg");
    assertTrue(c instanceof TodoCommand);
  }

  // Tests for Deadline
  @Test
  public void parse_deadlineNoArgs_invalidCommand() {
    Command c = Parser.parse("deadline");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_deadlineWhitespaceArgs_invalidCommand() {
    Command c = Parser.parse("deadline       ");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_deadlineBadDate_invalidCommand() {
    Command c = Parser.parse("deadline task /by 32/01/1950 0000");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_deadlineBadTime_invalidCommand() {
    Command c = Parser.parse("deadline task /by 01/01/1950 2401");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_deadlineFormat_invalidCommand() {
    Command c = Parser.parse("deadline task /by 01 01 1950 00:00");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_deadlineExpected_success() {
    Command c = Parser.parse("deadline task /by 01/01/1950 0000");
    assertTrue(c instanceof DeadlineCommand);
  }

  @Test
  public void parse_deadlineExpected1_success() {
    Command c = Parser.parse("deadline 1 egg /by 01/01/2090 0000");
    assertTrue(c instanceof DeadlineCommand);
  }

  // Tests for Event
  @Test
  public void parse_eventNoArgs_invalidCommand() {
    Command c = Parser.parse("event");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_eventWhitespaceArgs_invalidCommand() {
    Command c = Parser.parse("event       ");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_eventBadDate_invalidCommand() {
    Command c = Parser.parse("event task /from 01/13/1950 0000 /to 01/01/2000 0000");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_eventBadTime_invalidCommand() {
    Command c = Parser.parse("event task /from 01/01/1950 0000 /to 01/01/2000 9999");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_eventBadFormat_invalidCommand() {
    Command c = Parser.parse("event task /from 01-01-1950 0000 /to 01/01/2000 0000");
    assertTrue(c instanceof InvalidCommand);
  }

  @Test
  public void parse_eventExpected_success() {
    Command c = Parser.parse("event task /from 01/01/1950 0000 /to 01/01/2000 0000");
    assertTrue(c instanceof EventCommand);
  }

  @Test
  public void parse_eventExpected1_success() {
    Command c = Parser.parse("event 1 egg /from 01/01/2090 0000 /to 01/01/2090 0001");
    assertTrue(c instanceof EventCommand);
  }
}
