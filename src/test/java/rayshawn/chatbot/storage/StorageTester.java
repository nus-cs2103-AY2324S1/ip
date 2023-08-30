package rayshawn.chatbot.storage;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import rayshawn.chatbot.exceptions.ChatBotException;

public class StorageTester {

    private static final String TEST_DATA_FOLDER = "src/test/data/StorageTest.txt";

    @Test
    public void constructor_nullFilePath_exceptionThrown() throws Exception {
        assertThrows(NullPointerException.class, () -> new Storage(null));
    }

    @Test
    public void constructor_noTxtExtension_exceptionThrown() throws Exception {
        assertThrows(ChatBotException.class, () ->
                new Storage(TEST_DATA_FOLDER + "/" + "InvalidfileName"));
    }
}
