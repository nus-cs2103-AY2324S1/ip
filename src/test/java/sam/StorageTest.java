package sam;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sam.services.Storage;
import sam.services.TaskList;
import sam.services.parser.DateTimeParser;
import sam.tasks.Deadline;
import sam.tasks.Event;
import sam.tasks.Task;
import sam.tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StorageTest {
    private TaskList validTasks;
    private static final String TEST_FILE_VALID = "testdata/valid.txt";
    private static final String TEST_FILE_TEST = "testdata/test.txt";


    @BeforeEach
    public void setUp() throws IOException {
        validTasks = new TaskList();
        validTasks.addTask(new ToDo("buy book"));
        validTasks.addTask(new Deadline("return book", DateTimeParser.parseDate("2023-11-15 0800")));
        validTasks.addTask(new Event("pool party", DateTimeParser.parseDate("2019-11-15 0800"),
                DateTimeParser.parseDate("2023-11-15 0800")));
        File file = new File(TEST_FILE_VALID);
        File directory = file.getParentFile();

        if (!directory.exists()) {
            directory.mkdirs();
        }

        FileWriter fileWriter = new FileWriter(file);
        for (Task task : validTasks.getTasks()) {
            fileWriter.write(task.toFileString() + "\n");
        }
        fileWriter.close();
    }

    @Test
    public void loadTasksFromHardDisk() throws Exception {
        TaskList tasks = new TaskList();
        Storage storage = new Storage(TEST_FILE_VALID);
        storage.loadTasksFromFile(tasks);
        assertEquals(tasks.listTasks(), validTasks.listTasks());
    }

    @Test
    public void saveTasksToHardDisk() throws Exception {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("buy book"));
        tasks.addTask(new Deadline("return book", DateTimeParser.parseDate("2023-11-15 0800")));
        tasks.addTask(new Event("pool party", DateTimeParser.parseDate("2019-11-15 0800"),
                DateTimeParser.parseDate("2023-11-15 0800")));
        Storage storage = new Storage(TEST_FILE_TEST);
        storage.saveTasksToFile(tasks);
        String content1 = readFileContent(TEST_FILE_VALID);
        String content2 = readFileContent(TEST_FILE_TEST);
        assertEquals(content1, content2);
    }

    @AfterAll
    public static void tearDown() {
        File directory = new File(TEST_FILE_VALID).getParentFile();
        deleteDirectory(directory);
    }

    private static void deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                }
            }
        }
        directory.delete();
    }

    private String readFileContent(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        byte[] fileBytes = Files.readAllBytes(path);
        return new String(fileBytes);
    }

}
