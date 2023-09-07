package storage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import parser.DateTime;

import taskmanager.Deadline;
import taskmanager.Event;

import taskmanager.Task;
import taskmanager.ToDo;





class FileHandlerTest {

    @Test
    void testReadAndWriteTasks() throws IOException {
        // Create a temporary test file with known contents
        Path tempFile = Files.createTempFile("testFile", ".txt");
        FileHandler fileHandler = new FileHandler(tempFile.toString());

        ArrayList<Task> taskToWrite = new ArrayList<>();
        TaskList tasks = new TaskList(taskToWrite);

        DateTime dateTime = new DateTime();
        String formattedDate = dateTime.formatDateTime("23/08/2023 1000");

        ToDo newtodo = new ToDo("TestTodo");
        Deadline newdeadline = new Deadline("TestDeadline", formattedDate);
        Event newevent = new Event("TestEvent", formattedDate, formattedDate);

        tasks.add(newtodo);
        tasks.add(newdeadline);
        tasks.add(newevent);


        // Write the tasks to the test file
        fileHandler.writeTasksToFile(tasks);

        // Read tasks from the test file
        ArrayList<Task> tasksRead = fileHandler.readTasksFromFile();
        TaskList taskRead = new TaskList(tasksRead);

        // Perform assertions to compare the tasksToWrite and tasksRead
        // Since you've added a new ToDos task to tasksToWrite, make sure it's the same as tasksRead
        assertTrue(tasks.taskListEqual(taskRead));

        // Delete the temporary test file
        Files.deleteIfExists(tempFile);
    }
}
