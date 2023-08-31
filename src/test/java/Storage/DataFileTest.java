package Storage;

import org.junit.jupiter.api.Test;
import storage.DataFile;
import tasks.Deadline;
import tasks.Task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DataFileTest {
    @Test
    public void writeToFile_writeDeadline_success() {
        DataFile dF = new DataFile("src/test/data", "existing.txt");
        Task task = new Deadline("Study", LocalDateTime.parse("2019-04-04T03:00"));
        try {
            dF.writeToFile(task);
            BufferedReader br = new BufferedReader(new FileReader("src/test/data/existing.txt"));
            String line = br.readLine();
            assertEquals("D*|,Study*|,2019-04-04T03:00*|,0",line);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void deleteTaskFromFile_remDeadline_success() {
        DataFile dF = new DataFile("src/test/data", "tmp_del_task.txt");
        Task task = new Deadline("Study", LocalDateTime.parse("2019-04-04T03:00"));
        try {
            dF.writeToFile(task);
            dF.deleteTaskFromFile(0);
            BufferedReader br = new BufferedReader(new FileReader("src/test/data/tmp_del_task.txt"));
            String line = br.readLine();
            assertNull(line);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}
