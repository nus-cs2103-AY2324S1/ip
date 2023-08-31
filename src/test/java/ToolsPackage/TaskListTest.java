package ToolsPackage;

import DukePackage.DukeException;
import TaskPackage.Deadlines;
import TaskPackage.Events;
import TaskPackage.ToDos;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskListTest {

    @Test
    public void toggleDoneTest1() {
        try {
            TaskList tasks = new TaskList();
            Ui ui = new Ui();
            tasks.addItem(new ToDos("Todo 1", "0"), ui);
            tasks.addItem(new Deadlines("Deadline 1", "by 2023-08-31", "0"), ui);
            tasks.addItem(new Events("Event 1", "from 2023-08-30", "to 2023-08-31", "0"), ui);
            assertTrue(tasks.toggleDone("1", "mark", ui));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void toggleDoneTest2() {
        try {
            TaskList tasks = new TaskList();
            Ui ui = new Ui();
            tasks.addItem(new ToDos("Todo 1", "0"), ui);
            tasks.addItem(new Deadlines("Deadline 1", "by 2023-08-31", "0"), ui);
            tasks.addItem(new Events("Event 1", "from 2023-08-30", "to 2023-08-31", "0"), ui);
            assertFalse(tasks.toggleDone("5", "unmark", ui));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void removeItemTest1() {
        try {
            TaskList tasks = new TaskList();
            Ui ui = new Ui();
            tasks.addItem(new ToDos("Todo 1", "0"), ui);
            tasks.addItem(new Deadlines("Deadline 1", "by 2023-08-31", "0"), ui);
            tasks.addItem(new Events("Event 1", "from 2023-08-30", "to 2023-08-31", "0"), ui);
            assertTrue(tasks.removeItem("1", ui));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void removeItemTest2() {
        try {
            TaskList tasks = new TaskList();
            Ui ui = new Ui();
            tasks.addItem(new ToDos("Todo 1", "0"), ui);
            tasks.addItem(new Deadlines("Deadline 1", "by 2023-08-31", "0"), ui);
            tasks.addItem(new Events("Event 1", "from 2023-08-30", "to 2023-08-31", "0"), ui);
            assertFalse(tasks.removeItem("0", ui));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
