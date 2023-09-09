package noelPackage.helper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class StorageTest {

    @Test
    public void loadFileTest() {
        Storage testStorage = new Storage("./_loadFileTest.txt");
        String expected = "T | 1 | read\n" +
                "E | 1 | ori | (from: 2033-04-04 06:00 to: 2020-04-04 06:00)\n" +
                "D | 0 | read | 2023-04-03 06:00\n" +
                "T | 0 | read";
        assertEquals(expected, testStorage.checkFile());
    }

    @Test
    public void writeFileTest() {
        Storage writeStorage = new Storage("./_writeFileTest.txt");
        String taskString = "T | 1 | read";
        writeStorage.checkFile();

        List<String> listOfTasks = new ArrayList<>();
        listOfTasks.add(taskString);
        writeStorage.writeToFile(listOfTasks);

        assertEquals(taskString + "\n", writeStorage.checkFile());
    }
}
