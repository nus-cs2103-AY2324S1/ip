package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.utility.StorageStub;
import duke.utility.Ui;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testAddTask() {
        taskList.addTask("Buy groceries /by 06-03-2007 13:44", new StorageStub(), new Ui());
        assertEquals(1, taskList.getTaskListSize());
    }

    @Test
    public void testDeleteTask() {
        taskList.addTask("Buy groceries /by 06-03-2007 13:44", new StorageStub(), new Ui());
        taskList.deleteTask(1, new StorageStub(), new Ui());
        assertEquals(0, taskList.getTaskListSize());
    }

    @Test
    public void testShowAllTasks() {
        taskList.addTask("Buy groceries /by 06-03-2007 13:44", new StorageStub(), new Ui());
        taskList.addTask("Read book /from 02-09-2022 11:00 /to 02-09-2022 12:00", new StorageStub(), new Ui());
        taskList.addTask("Go for a walk", new StorageStub(), new Ui());
        assertEquals(3, taskList.getTaskListSize());
        Ui ui = new Ui();
        taskList.showAllTasks(ui, "sortBy description asc");
        String expectedOutput = "\nHere are the tasks in your list:\n"
                + "1.[D][ ] Buy groceries (by: 06-03-2007 13:44)\n"
                + "2.[T][ ] Go for a walk\n"
                + "3.[E][ ] Read book (from: 02-09-2022 11:00 to: 02-09-2022 12:00)\n\n";

        assertEquals(expectedOutput, ui.getOutput());
    }

    @Test
    public void testMarkTaskAsDone() {
        taskList.addTask("Buy groceries /by 06-03-2007 13:44", new StorageStub(), new Ui());
        taskList.markTaskAsDone(1, new StorageStub(), new Ui());
        assertEquals(true, taskList.getTask(0).getIsDone());
    }

    @Test
    public void testUnmarkTaskAsDone() {
        taskList.addTask("Buy groceries /by 06-03-2007 13:44", new StorageStub(), new Ui());
        taskList.markTaskAsDone(1, new StorageStub(), new Ui());
        taskList.unmarkTaskAsDone(1, new StorageStub(), new Ui());
        assertEquals(false, taskList.getTask(0).getIsDone());
    }
}
