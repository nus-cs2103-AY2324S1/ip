package dogebot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDos;

public class StorageTest {
    @Test
    public void readFromTxtFileSuccess() {
        // test input
        String home = System.getProperty("user.home"); // get relative path
        java.nio.file.Path path = java.nio.file.Paths.get(home, "OneDrive", "Desktop", "iP", "src", "main");
        File file = new File(path.toString(), "tasklistTest.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Storage storage = new Storage(file);
        ArrayList<Task> actual = storage.readFromTxtFile().getTasks();

        // expected
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(new ToDos("eat cereal", true));
        expected.add(new Deadline("buy milk", "5/9/2023 1523", true, "3/9/2023 1523"));
        expected.add(new Event("project meeting", "3/9/2023 1400", "15/10/2023 0800", false,
                "1/9/2023 1400"));
        expected.add(new Event("finish cereal", "5/9/2023 0800", "5/9/2023 1300", false,
                "3/9/2023 0800"));

        for (int i = 0; i < expected.size(); i++) {
            String a = expected.get(i).toString();
            String b = actual.get(i).toString();
            assertEquals(a, b);
        }
    }
}
