package friday;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class StorageTest {

    private static final String TEST_FILE_PATH = "testData/testTasks.txt";

    @Test
    public void testSaveFile() throws IOException {
        Storage storage = new Storage(TEST_FILE_PATH);
        String sampleTaskList = "Sample Task 1\n";

        // Save the sample task list to file
        storage.saveFile(sampleTaskList);

        // Read the saved file and compare
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(TEST_FILE_PATH))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                contentBuilder.append(currentLine).append("\n");
            }
        }

        String content = contentBuilder.toString();
        assertEquals(sampleTaskList, content);

        // Cleanup
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }
}
