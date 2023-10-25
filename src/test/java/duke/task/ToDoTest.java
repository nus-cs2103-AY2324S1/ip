package duke.task;
import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

	@Test
	public void writeToFileToDoTest() {
		ToDo toDo = new ToDo("hello");
		String expected = "T | 0 | hello";
		String actual =  toDo.writeToFile();
		assertEquals(expected, actual);
	}

	@Test
	public void toStringTest() {
		ToDo toDo = new ToDo("hello");
		String expected = "[T][ ] hello";
		String actual = toDo.toString();

		assertEquals(expected, actual);
	}


}
