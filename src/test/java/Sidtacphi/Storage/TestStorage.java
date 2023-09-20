package Sidtacphi.Storage;

import java.io.IOException;
import java.lang.StringBuilder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import Sidtacphi.Task.TaskType;
import Sidtacphi.Exception.SidException;
import Sidtacphi.Task.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStorage {
    @Test
    public void testSaveAsJson() {
        Path path = Path.of("./src/test/java/Sidtacphi/Storage/tasks-temp.json");
        
        String savedJsonString = "";
        StringBuilder contentBuilder = new StringBuilder();
        try {
            TaskList taskList = new TaskList();
            taskList.addTask(TaskType.DEADLINE, "deadline return book /by 2023-06-01");
            Storage.saveAsJson(taskList, "./src/test/java/Sidtacphi/Storage/tasks-temp.json");

            Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8);
            stream.forEach(s -> contentBuilder.append(s));
            savedJsonString = contentBuilder.toString();

            Files.deleteIfExists(path);

            assertEquals("[{\"type\":\"deadline\",\"name\":\"return book\",\"isCompleted\":false,\"deadline\":\"2023-06-01\"}]",
                savedJsonString);
        } catch (IOException ioException) {
            System.out.println(ioException);
            System.out.println("Improperly written testSaveAsJson");
            assertEquals(0, 1);
        } catch (SidException sidException) {
            System.out.println(sidException);
            System.out.println("Improperly written testSaveAsJson");
            assertEquals(0, 1);
        }
    }

    @Test
    public void testReadJson() {
        Path path = Path.of("./src/test/java/Sidtacphi/Storage/tasks-temp.json");
        
        try {
            TaskList taskList1 = new TaskList();
            taskList1.addTask(TaskType.DEADLINE, "deadline return book /by 2023-06-01");
            Storage.saveAsJson(taskList1, "./src/test/java/Sidtacphi/Storage/tasks-temp.json");
            
            TaskList taskList2 = Storage.readJson("./src/test/java/Sidtacphi/Storage/tasks-temp.json");

            Files.deleteIfExists(path);

            assertEquals(taskList1, taskList2);
        } catch (IOException ioException) {
            System.out.println(ioException);
            System.out.println("Improperly written testReadJson");
            assertEquals(0, 1);
        } catch (SidException sidException) {
            System.out.println(sidException);
            System.out.println("Improperly written testReadJson");
            assertEquals(0, 1);
        }
    }
}
