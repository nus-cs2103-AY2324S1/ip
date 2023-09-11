package seedu.duke;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Encapsulates the StorageTest class.
 * The StorageTest class contains unit tests for methods in the Storage class.
 */
public class StorageTest {

    /**
     * Tests the save method in Storage.
     */
    @Test
    public void save_normalTaskArrayList_success(){
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new ToDo("borrow books"));
        taskList.add(new Deadline("read books",
                        LocalDateTime.of(2023, 8, 23, 18, 00)));
        taskList.add(new Event("return books",
                        LocalDateTime.of(2023, 8, 23, 18, 00),
                        LocalDateTime.of(2023, 8, 23, 18, 01)));
        new Storage("./data/duke.txt").save(taskList);

        String correctList = "1.[T][ ] borrow books \n" +
                "2.[D][ ] read books (by: August 23 2023, 6:00 PM)\n" +
                "3.[E][ ] return books (from: August 23 2023, 6:00 PM to: August 23 2023, 6:01 PM)";

        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./data/duke.txt"));
            ArrayList<Task> loadedTasks = (ArrayList<Task>) inputStream.readObject();
            String currentList = "";

            for (int i = 0; i < loadedTasks.size(); i++) {
                currentList += i+1 + "." + loadedTasks.get(i).getTaskType() + loadedTasks.get(i).getStatusIcon() + " " + loadedTasks.get(i).name +
                                " " + loadedTasks.get(i).getTimeInfo();
                if (i != loadedTasks.size() - 1) {
                    currentList += "\n";
                }
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
     * Tests the load method in Storage.
     */
    @Test
    public void load_normalTaskArrayList_success(){
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new ToDo("borrow books"));
        taskList.add(new Deadline("read books",
                        LocalDateTime.of(2023, 8, 23, 18, 00)));
        taskList.add(new Event("return books",
                        LocalDateTime.of(2023, 8, 23, 18, 00),
                        LocalDateTime.of(2023, 8, 23, 18, 01)));

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./data/duke.txt", false))) {
            outputStream.writeObject(taskList);
        } catch (IOException e) {
            fail();
        }

        String correctList = "1.[T][ ] borrow books \n" +
                "2.[D][ ] read books (by: August 23 2023, 6:00 PM)\n" +
                "3.[E][ ] return books (from: August 23 2023, 6:00 PM to: August 23 2023, 6:01 PM)";

        try {
            ArrayList<Task> tasks = new Storage("./data/duke.txt").load();
            String currentList = "";

            for (int i = 0; i < tasks.size(); i++) {
                currentList += i+1 + "." + tasks.get(i).getTaskType() + tasks.get(i).getStatusIcon() + " " + tasks.get(i).name +
                        " " + tasks.get(i).getTimeInfo();

                if (i != tasks.size() - 1) {
                    currentList += "\n";
                }
            }

            assertEquals(correctList, currentList);
        } catch (InvalidDataFormatException e) {
            fail();
        }
    }
}
