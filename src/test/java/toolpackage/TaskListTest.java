package toolpackage;

import dukepackage.DukeException;

import taskpackage.Deadlines;
import taskpackage.Events;
import taskpackage.ToDos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskListTest {

    @Test
    public void toggleDone_trueReturned() {
        try {
            Ui ui = new Ui();

            TaskList tasks = new TaskList();
            tasks.addItem(new ToDos("Todo 1", "0"), ui);
            tasks.addItem(new Deadlines("Deadline 1", "by 2023-08-31", "0"), ui);
            tasks.addItem(new Events("Event 1", "from 2023-08-30", "to 2023-08-31", "0"), ui);

            assertTrue(tasks.toggleDone("1", "mark", ui));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void toggleDone_incorrectIndex_falseReturned() {
        try {
            Ui ui = new Ui();

            TaskList tasks = new TaskList();
            tasks.addItem(new ToDos("Todo 1", "0"), ui);
            tasks.addItem(new Deadlines("Deadline 1", "by 2023-08-31", "0"), ui);
            tasks.addItem(new Events("Event 1", "from 2023-08-30", "to 2023-08-31", "0"), ui);

            assertFalse(tasks.toggleDone("5", "unmark", ui));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void removeItem_trueReturned() {
        try {
            Ui ui = new Ui();

            TaskList tasks = new TaskList();
            tasks.addItem(new ToDos("Todo 1", "0"), ui);
            tasks.addItem(new Deadlines("Deadline 1", "by 2023-08-31", "0"), ui);
            tasks.addItem(new Events("Event 1", "from 2023-08-30", "to 2023-08-31", "0"), ui);

            assertTrue(tasks.removeItem("1", ui));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void removeItem_incorrectIndex_falseReturned() {
        try {
            Ui ui = new Ui();

            TaskList tasks = new TaskList();
            tasks.addItem(new ToDos("Todo 1", "0"), ui);
            tasks.addItem(new Deadlines("Deadline 1", "by 2023-08-31", "0"), ui);
            tasks.addItem(new Events("Event 1", "from 2023-08-30", "to 2023-08-31", "0"), ui);
            
            assertFalse(tasks.removeItem("0", ui));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
