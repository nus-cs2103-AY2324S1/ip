import org.junit.jupiter.api.Test;
import types.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStorage {

    @Test
    public void testAddToListCommas() {
        try {
            Path path = Paths.get("barbie.txt");
            Storage.addToList(path, "do, work,");
            List<String> list = Files.readAllLines(path);
            String actual = list.get(list.size() - 1);
            assertEquals("T,0,do, work,", actual);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testGetListWithCommas() {
        ArrayList<Task> oldList = Storage.getLastList();
        String actual = oldList.get(oldList.size() - 1).toString();

        assertEquals("[T][ ] do", actual);

    }


}
