package duke.main;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


class StorageTest {
    private String testFilePath = "testData.txt";
    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage(testFilePath);
    }

    @Test
    public void loadData_fileDoesntExist_createsNewTaskManager() {
        String randomFileName = "randomfilename.txt";
        Storage tempStorage = new Storage(randomFileName);
        TaskManager taskManager = assertDoesNotThrow(() -> tempStorage.loadData());
        assertNotNull(taskManager);
    }

    @Test
    public void testCreateFile_fileDoesntExit_createsNewFile() {
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete();
        }
        assertDoesNotThrow(() -> storage.createFile());
        assertTrue(file.exists());
    }
}