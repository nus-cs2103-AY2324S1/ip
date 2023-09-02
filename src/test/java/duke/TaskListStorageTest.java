package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

public class TaskListStorageTest {

    @Test
    public void testLoadTaskList() throws NoSuchFieldException, IllegalAccessException {
        String filepath = "./data/test.txt";
        try {
            FileWriter fw = new FileWriter(filepath);
            fw.write("T | 0 | test todo\n");
            fw.write("D | 0 | test deadline | 2022-12-31\n");
            fw.write("E | 1 | test event | 2022-12-31 | 2023-01-01\n");
            fw.close();
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

        TaskListStorage storage = new TaskListStorage(filepath);
        ArrayList<Task> expectedTaskList = new ArrayList<>();
        expectedTaskList.add(new Todo("test todo", false));
        expectedTaskList.add(new Deadline("test deadline", LocalDate.parse("2022-12-31"), false));
        expectedTaskList
                .add(new Event("test event", LocalDate.parse("2022-12-31"), LocalDate.parse("2023-01-01"), true));

        Field actualTaskListField = TaskListStorage.class.getDeclaredField("taskList");
        actualTaskListField.setAccessible(true);
        ArrayList<Task> actualTaskList = (ArrayList<Task>) actualTaskListField.get(storage);

        assertEquals(expectedTaskList.size(), actualTaskList.size());
        for (int i = 0; i < expectedTaskList.size(); i++) {
            assertEquals(expectedTaskList.get(i).toString(), actualTaskList.get(i).toString());
        }

        File file = new File(filepath);
        file.delete();
    }

    @Test
    public void testWriteTaskListToFile() throws IOException {
        String filepath = "./data/test.txt";
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new Todo("test todo", false));
        taskList.add(new Deadline("test deadline", LocalDate.parse("2022-12-31"), true));
        taskList.add(new Event("test event", LocalDate.parse("2022-12-31"), LocalDate.parse("2023-01-01"), false));

        TaskListStorage storage = new TaskListStorage(filepath);
        Method writeTaskListToFileMethod;
        try {
            writeTaskListToFileMethod = TaskListStorage.class.getDeclaredMethod("writeTaskListToFile", ArrayList.class,
                    String.class);
            writeTaskListToFileMethod.setAccessible(true);
            writeTaskListToFileMethod.invoke(storage, taskList, filepath);
        } catch (NoSuchMethodException | SecurityException e) {
            fail("Exception should not have been thrown");
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }

        String expectedContent = "T | 0 | test todo\nD | 1 | test deadline | 2022-12-31\nE | 0 | test event | 2022-12-31 | 2023-01-01\n";
        String actualContent = new String(Files.readAllBytes(Paths.get(filepath)));

        assertEquals(expectedContent, actualContent);

        File file = new File(filepath);
        file.delete();
    }

}