package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
	@Test
	public void addTest() {
		TaskList list = new TaskList(new ArrayList<>());
		list.addTask(new Todo("read book"));
		assertEquals("[T][ ] read book",list.getTask(0).toString());
	}

	@Test
	public void deleteTest() {
		TaskList list = new TaskList(new ArrayList<>());
		list.addTask(new Todo("read book"));
		list.addTask(new Todo("study"));
		list.deleteTask(0);
		assertEquals("[T][ ] study",list.getTask(0).toString());
	}
}
