package duke.parser;

import duke.command.AddTodoCommand;
import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.ExitCommand;
import duke.command.WrongCommand;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

	@Test
	public void success_AddTodo() {
		Parser parser = new Parser();
		try {
			assertEquals(Parser.parse("todo return book").getClass(), AddTodoCommand.class);
		} catch (DukeException e) {
			fail();
		}
	}

	@Test
	public void addTodo_noDescription() {
		Parser parser = new Parser();
		try {
			Parser.parse("todo ");
			fail();
		} catch (DukeException e) {
			assertEquals("The description of a todo cannot be empty.", e.getMessage());
		}
	}

	@Test
	public void success_AddDeadline() {
		Parser parser = new Parser();
		try {
			assertEquals(Parser.parse("deadline return book /by 2023-09-30").getClass(), AddDeadlineCommand.class);
		} catch (DukeException e) {
			fail();
		}
	}

	@Test
	public void addDeadline_noDescription() {
		Parser parser = new Parser();
		try {
			Parser.parse("deadline /by 2923-09-30");
			fail();
		} catch (DukeException e) {
			assertEquals("The description of a deadline cannot be empty.", e.getMessage());
		}
	}

	@Test
	public void addDeadline_noDue() {
		Parser parser = new Parser();
		try {
			Parser.parse("deadline return book ");
			fail();
		} catch (DukeException e) {
			assertEquals("A deadline task requires a /by (timedate) descriptor", e.getMessage());
		}
	}

	@Test
	public void addDeadline_wrongTimeFormat() {
		Parser parser = new Parser();
		try {
			Parser.parse("deadline return book /by 2");
			fail();
		} catch (DukeException e) {
			assertEquals("Please represent time in a proper time format of yyyy-mm-dd", e.getMessage());
		}
	}

	@Test
	public void success_AddEvent() {
		Parser parser = new Parser();
		try {
			assertEquals(Parser.parse("event read book /from 2023-09-30 /to 2023-10-10").getClass(),
					AddEventCommand.class);
		} catch (DukeException e) {
			fail();
		}
	}

	@Test
	public void addEvent_noDescription() {
		Parser parser = new Parser();
		try {
			Parser.parse("event /from 2023-09-30 /to 2023-10-10");
			fail();
		} catch (DukeException e) {
			assertEquals("The description of an event cannot be empty.", e.getMessage());
		}
	}

	@Test
	public void addEvent_noFrom() {
		Parser parser = new Parser();
		try {
			Parser.parse("event read book ");
			fail();
		} catch (DukeException e) {
			assertEquals("An event requires a /from (timedate) descriptor", e.getMessage());
		}
	}

	@Test
	public void addEvent_noTo() {
		Parser parser = new Parser();
		try {
			Parser.parse("event read book /from 2023-09-30");
			fail();
		} catch (DukeException e) {
			assertEquals("An event requires a /to (timedate) descriptor", e.getMessage());
		}
	}

	@Test
	public void addEvent_wrongTimeFormat() {
		Parser parser = new Parser();
		try {
			Parser.parse("event read book /from 2 /to 3");
			fail();
		} catch (DukeException e) {
			assertEquals("Please represent time in a proper time format of yyyy-mm-dd", e.getMessage());
		}
	}

	@Test
	public void markTask_success() {
		Parser parser = new Parser();
		try {
			assertEquals(Parser.parse("mark 1").getClass(), MarkCommand.class);
		} catch (DukeException e) {
			fail();
		}
	}

	@Test
	public void markTask_noIndex() {
		Parser parser = new Parser();
		try {
			Parser.parse("mark ");
			fail();
		} catch (DukeException e) {
			assertEquals("Please select a task using its index", e.getMessage());
		}
	}

	@Test
	public void markTask_notNumber() {
		Parser parser = new Parser();
		try {
			Parser.parse("mark hello");
			fail();
		} catch (DukeException e) {
			assertEquals("Please specify the index of the task (Numbers only)", e.getMessage());
		}
	}

	@Test
	public void unmarkTask_success() {
		Parser parser = new Parser();
		try {
			assertEquals(Parser.parse("unmark 1").getClass(), UnmarkCommand.class);

		} catch (DukeException e) {
			fail();
		}
	}

	@Test
	public void unmarkTask_noIndex() {
		Parser parser = new Parser();
		try {
			Parser.parse("unmark ");
			fail();
		} catch (DukeException e) {
			assertEquals("Please select a task using its index", e.getMessage());
		}
	}

	@Test
	public void unmarkTask_notNumber() {
		Parser parser = new Parser();
		try {
			Parser.parse("unmark hello");
			fail();
		} catch (DukeException e) {
			assertEquals("Please specify the index of the task (Numbers only)", e.getMessage());
		}
	}

	@Test
	public void deleteTask_success() {
		Parser parser = new Parser();
		try {
			assertEquals(Parser.parse("delete 1").getClass(), DeleteCommand.class);
		} catch (DukeException e) {
			fail();
		}
	}

	@Test
	public void deleteTask_noIndex() {
		Parser parser = new Parser();
		try {
			Parser.parse("delete ");
			fail();
		} catch (DukeException e) {
			assertEquals("Please select a task using its index", e.getMessage());
		}
	}

	@Test
	public void deleteTask_notNumber() {
		Parser parser = new Parser();
		try {
			Parser.parse("delete hello");
			fail();
		} catch (DukeException e) {
			assertEquals("Please specify the index of the task (Numbers only)", e.getMessage());
		}
	}

	@Test
	public void listCommand_success() {
		Parser parser = new Parser();
		try {
			assertEquals(parser.parse("list").getClass(), ListCommand.class);
		} catch (DukeException e) {
			fail();
		}
	}

	@Test
	public void exitCommand_success() {
		Parser parser = new Parser();
		try {
			assertEquals(parser.parse("bye").getClass(), ExitCommand.class);
		} catch (DukeException e) {
			fail();
		}
	}

	@Test
	public void wrongCommand_success() {
		Parser parser = new Parser();
		try {
			assertEquals(parser.parse("hello").getClass(), WrongCommand.class);
		} catch (DukeException e) {
			fail();
		}
	}
}
