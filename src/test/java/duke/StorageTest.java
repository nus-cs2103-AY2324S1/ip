package duke;

import duke.task.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test 
    public void testLoadFile() {
        String directoryPath = "./data";
        String filePath = "./data/test1.txt";
        Storage storage = new Storage(filePath, directoryPath);
        TaskList taskList = storage.loadFile();

        assertEquals(3, taskList.getSize());

        Task task1 = taskList.getTask(0);
        Task task1Expected = new Deadline("deadline buy soju /by 2023-08-26 2300");
        assertEquals(task1.toString(), task1Expected.toString());

        Task task2 = taskList.getTask(1);
        Task task2Expected = new Event("event orbital splashdown /from 2023-08-30 1700 /to 2023-08-30 1930");
        task2Expected.markAsDone();
        assertEquals(task2.toString(), task2Expected.toString());

        Task task3 = taskList.getTask(2);
        Task task3Expected = new ToDo ("todo eat mcgriddles");
        assertEquals(task3.toString(), task3Expected.toString());
    }

    @Test 
    public void emptyFile() {
        String directoryPath = "";
        String filePath = "";
        Storage storage = new Storage(filePath, directoryPath);
        TaskList taskList = storage.loadFile();
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void testSaveFile() {
        String directoryPath = "./data";
        String filePath = "./data/test2.txt";
        Storage storage = new Storage(filePath, directoryPath);
        TaskList taskList = storage.loadFile();

        Task task1 = new Deadline("deadline buy soju /by 2023-08-26 2300");
        taskList.addTask(task1);
        storage.saveFile(taskList);
        TaskList taskList2 = storage.loadFile();
        assertEquals(1, taskList2.getSize());
        assertEquals(task1.toString(), taskList2.getTask(0).toString());
        taskList2.deleteTask(0);
        storage.saveFile(taskList2);
    }
}
