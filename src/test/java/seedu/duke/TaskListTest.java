package seedu.duke;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Encapsulates the TaskListTest class.
 * The TaskListTest class contains unit tests for methods in the TaskList class.
 */
public class TaskListTest {

    /**
     * Tests the addTask method in TaskList.
     */
    @Test
    public void addTask_normalTask_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("borrow books"));

        try {
            ArrayList<Task> currentTasks;
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./data/duke.txt"));
            currentTasks = (ArrayList<Task>) inputStream.readObject();

            String correctList = "1.[T][ ] borrow books ";
            String currentList = "";

            for (int i = 0; i < currentTasks.size(); i++) {
                currentList += i + 1 + "." + currentTasks.get(i).getTaskType() + currentTasks.get(i).getStatusIcon() + " " + currentTasks.get(i).name +
                        " " + currentTasks.get(i).getTimeInfo();
            }

            assertEquals(correctList, currentList);
        } catch (FileNotFoundException e) {
            fail();
        } catch (IOException e) {
            fail();
        } catch (ClassNotFoundException e) {
            fail();
        }
    }

    /**
     * Tests the markOrDeleteTask method in TaskList.
     */
    @Test
    public void markOrDeleteTask_markOne_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("borrow books"));
        TaskList taskList = new TaskList(tasks);
        try {
            taskList.markOrDeleteTask(0, "mark");
            ArrayList<Task> currentTasks;
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./data/duke.txt"));
            currentTasks = (ArrayList<Task>) inputStream.readObject();

            String correctList = "1.[T][X] borrow books ";
            String currentList = "";

            for (int i = 0; i < currentTasks.size(); i++) {
                currentList += i + 1 + "." + currentTasks.get(i).getTaskType() + currentTasks.get(i).getStatusIcon() + " " + currentTasks.get(i).name +
                        " " + currentTasks.get(i).getTimeInfo();
            }

            assertEquals(correctList, currentList);
        } catch (InvalidDataFormatException e) {
            fail();
        } catch (FileNotFoundException e) {
            fail();
        } catch (IOException e) {
            fail();
        } catch (ClassNotFoundException e) {
            fail();
        }
    }
}
