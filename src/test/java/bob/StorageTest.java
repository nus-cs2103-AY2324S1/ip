package bob;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    private static String testFilePath = "src/test/tasks.txt";

    @Test
    public void testLoad() {
        Storage storage = new Storage(testFilePath);
        ArrayList<Task> tasks = new ArrayList<Task>();

        tasks.add(new Todo("task one"));
        tasks.add(new Deadline("task two", LocalDate.parse("2019-08-08")));
        tasks.add(new Event("task three", LocalDate.parse("2019-05-02"),
                    LocalDate.parse("2020-01-01")));

        TaskList list = new TaskList(tasks);
        storage.saveNewList(list);

        try {
            assertEquals(tasks.get(0).getDescription(), storage.load().get(0).getDescription());
        } catch (BobException e) {
            System.out.println((e.getMessage()));
        }

    }

    @Test
    public void testSave() {
        Storage storage = new Storage(testFilePath);
        ArrayList<Task> tasks = new ArrayList<Task>();

        tasks.add(new Todo("task one"));
        tasks.add(new Deadline("task two", LocalDate.parse("2019-08-08")));
        tasks.add(new Event("task three", LocalDate.parse("2019-05-02"),
                LocalDate.parse("2020-01-01")));

        TaskList list = new TaskList(tasks);
        storage.saveNewList(list);
        File file = new File(testFilePath);
        try {
            Scanner s = new Scanner(file);
            assertEquals("T,0,task one", s.nextLine());
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
