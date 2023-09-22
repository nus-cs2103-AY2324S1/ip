package boti.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import boti.storage.StorageSample;
import boti.task.Task;
import boti.task.TaskList;
import boti.task.ToDo;

/**
 * Class for Ui testing
 */
public class UiTest {
    private static final String SPACE = "    ";
    private UiSample ui;
    private TaskList tasks;
    private TaskList copyTasks;
    private StorageSample storage;

    /**
     * Sets things up before each tests
     */
    @BeforeEach
    public void setup() {
        this.ui = new UiSample();
        this.tasks = new TaskList(new ArrayList<>());
        this.copyTasks = new TaskList(new ArrayList<>());
        this.storage = new StorageSample("./data", "test.txt");
    }

    /**
     * Tests the ui method to print task
     */
    @Test
    public void ui_printToDo_success() {
        Task task = new ToDo("Test");
        String type = "todo";
        int size = 1;
        String result = ui.printAddTask(task, size);

        String expected = SPACE + "Got it. I've added this " + type + ":\n"
                + SPACE + task + "\n"
                + SPACE + "Now you have " + size + " tasks in the list.";
        assertEquals(ui.getPrintAddTaskCount(), 1);
        assertEquals(result, expected);
    }

    /**
     * Tests the ui method to print a list of non empty tasks
     */
    @Test
    public void ui_printList_success() {
        Task firstTask = new ToDo("Test");
        Task secondTask = new ToDo("Another Test");
        Task thirdTask = new ToDo("Final Test");

        tasks.addTask(firstTask);
        tasks.addTask(secondTask);
        tasks.addTask(thirdTask);

        String result = ui.printTasks(tasks);
        String expected = SPACE + "Here are the tasks in your list:\n"
                        + tasks;
        assertEquals(ui.getPrintTasksCount(), 1);
        assertEquals(result, expected);
    }

    /**
     * Tests the ui method to print an empty list of tasks
     */
    @Test
    public void ui_printEmptyList_success() {
        String result = ui.printTasks(tasks);
        String expected = SPACE + "Currently you have no tasks.";
        assertEquals(ui.getPrintTasksCount(), 1);
        assertEquals(result, expected);
    }
}
