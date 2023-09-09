package friday;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import org.junit.jupiter.api.Test;

public class StorageTest {

    private static final String TEST_FILE_PATH = "testData/testTasks.txt";

    /**
     * Tests the saveFile method of the Storage class.
     * It saves a sample task list to a file, then reads the file to verify
     * the contents match the sample task list.
     *
     * @throws IOException if there's an error reading or writing to the test file.
     */
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
        assert sampleTaskList.equals(content) : "Mismatch between saved content and expected content.";

        // Cleanup
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    public static void main(String[] args) throws IOException {
        StorageTest test = new StorageTest();
        test.testSaveFile();
    }
}
