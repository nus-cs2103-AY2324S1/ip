package Storage;

import Parser.DateTime;
import TaskManager.Deadlines;
import TaskManager.Events;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;
import TaskManager.Tasks;
import TaskManager.ToDos;
import Ui.Ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FileHandlerTest {

    @Test
    void testReadAndWriteTasks() throws IOException {
        // Create a temporary test file with known contents
        Path tempFile = Files.createTempFile("testFile", ".txt");
        FileHandler fileHandler = new FileHandler(tempFile.toString());

        ArrayList<Tasks> tasksToWrite = new ArrayList<>();
        TaskList tasks = new TaskList(tasksToWrite);

        DateTime dateTime = new DateTime();
        String formattedDate = dateTime.formatDateTime("23/08/2023 1800");

        ToDos newtodo = new ToDos("TestTodo");
        Deadlines newdeadline = new Deadlines("TestDeadline", formattedDate);
        Events newevent = new Events("TestEvent", formattedDate, formattedDate);

        tasks.add(newtodo);
        tasks.add(newdeadline);
        tasks.add(newevent);


        // Write the tasks to the test file
        fileHandler.writeTasksToFile(tasks);

        // Read tasks from the test file
        ArrayList<Tasks> tasksRead = fileHandler.readTasksFromFile();
        TaskList taskRead = new TaskList(tasksRead);

        // Perform assertions to compare the tasksToWrite and tasksRead
        // Since you've added a new ToDos task to tasksToWrite, make sure it's the same as tasksRead
        assertTrue(tasks.taskListEqual(taskRead));

        // Delete the temporary test file
        Files.deleteIfExists(tempFile);
    }
}