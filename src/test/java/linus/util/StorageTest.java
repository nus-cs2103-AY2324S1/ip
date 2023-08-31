package linus.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import linus.task.Task;
import linus.exception.LinusException;

public class StorageTest {
    String SERIALIZED_LIST = "[" +
            "{'type':'linus.task.ToDo','description':'task 1', 'isDone':false}," +
            "{'type':'linus.task.Deadline','description':'task 2','isDone':false,'by':'2023-08-31'}," +
            "{'type':'linus.task.Event','description':'task 3','isDone':false,'from':'2023-08-31','to':'2023-09-01'}" +
            "]";

    @Test
    public void load_loadTextFile_objectsSuccessfullyDeserialized() {
        try {
            FileWriter fileWriter = new FileWriter("data/linus_test.txt");
            fileWriter.write(SERIALIZED_LIST);
            fileWriter.close();

            Storage storage = new Storage("data/linus_test.txt");
            List<Task> tasks = storage.load();
            assertEquals(3, tasks.size());
            assertEquals("[T][ ] task 1", tasks.get(0).toString());
            assertEquals("[D][ ] task 2 (by: Aug 31 2023)", tasks.get(1).toString());
            assertEquals("[E][ ] task 3 (from: Aug 31 2023 to: Sep 1 2023)", tasks.get(2).toString());
        } catch (IOException e) {
            fail("IOException thrown while executing the test case");
        } catch (LinusException e) {
            fail("LinusException thrown while executing the test case");
        }
    }

}
