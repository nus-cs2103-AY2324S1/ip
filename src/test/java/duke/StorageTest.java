package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.exception.DukeBadInputException;

public class StorageTest {

    @Test
    public void checkFile_correctInput() {
        String[] expected = new String[]{"TODO#test#0",
            "TODO#test1#1",
            "DEADLINE#test2#0#2023-10-01T23:59",
            "DEADLINE#test3#0#2024-01-01T12:00",
            "DEADLINE#test4#0#2024-05-05T12:00",
            "EVENT#test5#1#2023-05-07T03:03#2023-05-07T03:05"};

        try {
            Storage storage = new Storage("data/testData.txt");
            List<String> ret = storage.readFile();
            assertEquals(expected.length, ret.size());
            for (int i = 0; i < expected.length; i++) {
                assertEquals(expected[i], ret.get(i));
            }
        } catch (IOException | DukeBadInputException e) {
            fail();
        }
    }

    @Test
    public void checkFile_notFile_exceptionThrown() {
        try {
            new Storage("data/Data");
            fail();
            new Storage("data/Data.json");
            fail();
        } catch (IOException | DukeBadInputException e) {
            System.out.println(e.getMessage());
        }
    }
}
