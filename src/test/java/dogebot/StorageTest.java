package dogebot;

import org.junit.jupiter.api.Test;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void readFromTxtFileSuccess() {
        // test input
        String HOME = System.getProperty("user.home"); // get relative path
        java.nio.file.Path PATH = java.nio.file.Paths.get(HOME, "OneDrive", "Desktop", "iP", "src", "main");
        File file = new File(PATH.toString(), "tasklistTest.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Storage storage = new Storage(file);
        ArrayList<Task> actual = storage.readFromTxtFile();

        // expected
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(new ToDos("eat cereal", true));
        expected.add(new Deadline("buy milk", "5/9/2023 1523", true));
        expected.add(new Event("project meeting", "3/9/2023 1400", "15/10/2023 0800", false));
        expected.add(new Event("finish cereal", "5/9/2023 0800", "5/9/2023 1300", false));

        for (int i = 0; i < expected.size(); i++) {
            String a = expected.get(i).toString();
            String b = actual.get(i).toString();
            assertEquals(a, b);
        }
    }
}
