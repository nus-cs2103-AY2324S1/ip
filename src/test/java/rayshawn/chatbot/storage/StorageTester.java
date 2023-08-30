package rayshawn.chatbot.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import rayshawn.chatbot.exceptions.ChatBotException;

public class StorageTester {

    @TempDir
    public static Path testFolder;

    private static final String TEST_DATA_FOLDER = "src/test/data/StorageTest.txt";
    private static final String NON_EXISTENT_FILE_NAME = "ThisFileDoesNotExist.txt";

    @Test
    public void constructor_nullFilePath_execeptionThrown() throws Exception {
        assertThrows(NullPointerException.class, () -> new Storage(null));
    }

    @Test
    public void constructor_noTxtExtension_exceptionThrown() throws Exception {
        assertThrows(ChatBotException.class, () ->
                new Storage(TEST_DATA_FOLDER + "/" + "InvalidfileName"));
    }
}
