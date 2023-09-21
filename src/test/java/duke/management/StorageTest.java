package duke.management;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import duke.note.Note;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class StorageTest {
    private static Storage storage;
    private static File testTasksFile;
    private static File testNotesFile;

    @BeforeAll
    public static void setUp() throws IOException {
        // Create a temporary test file
        testTasksFile = File.createTempFile("data", ".txt");
        testNotesFile = File.createTempFile("notes", ".txt");
        String testDataFileName = testTasksFile.getName();
        String testNotesFileName = testNotesFile.getName();
        String directoryPath = testTasksFile.getParent();

        // Write some sample task data to the test tasks file
        FileWriter taskWriter = new FileWriter(testTasksFile);
        taskWriter.write("T | 1 | Buy groceries\n");
        taskWriter.write("D | 0 | Submit report | 30-09-2023 1000\n");
        taskWriter.write("E | 1 | Team meeting | 10-09-2023 1400 | 10-09-2023 1600\n");
        taskWriter.close();

        // Write some sample ntoes data to the test notes file
        FileWriter notesWriter = new FileWriter(testNotesFile);
        notesWriter.write("Buy groceries\n");
        notesWriter.write("Submit report\n");
        notesWriter.write("E = mc^2");
        notesWriter.close();

        // Initialize the Storage object with the test files
        storage = new Storage(directoryPath, testDataFileName, testNotesFileName);
    }

    // Test the 2 load methods to ensure they load the data files properly and all created tasks and notes are the same.
    @Test
    public void testLoadTasks() {
        ArrayList<Task> loadedTasks = storage.loadTasks();

        Task todo = new Todo("Buy groceries");
        todo.markAsDone();
        Task deadline = new Deadline("Submit report", "30-09-2023 1000");
        Task event = new Event("Team meeting", "10-09-2023 1400", "10-09-2023 1600");
        event.markAsDone();

        // Check if the loaded tasks match the expected tasks
        ArrayList<Task> expectedTasks = new ArrayList<>(Arrays.asList(
                todo,
                deadline,
                event
        ));

        assertEquals(expectedTasks.size(), loadedTasks.size());

        for (int i = 0; i < expectedTasks.size(); i++) {
            assertEquals(expectedTasks.get(i).toString(), loadedTasks.get(i).toString());
        }
    }

    @Test
    public void testLoadNotes() {
        ArrayList<Note> loadedNotes = storage.loadNotes();

        Note note1 = new Note("Buy groceries");
        Note note2 = new Note("Submit report");
        Note note3 = new Note("E = mc^2");

        ArrayList<Note> expectedNotes = new ArrayList<>(Arrays.asList(
                note1,
                note2,
                note3
        ));

        assertEquals(expectedNotes.size(), loadedNotes.size());

        for (int i = 0; i < expectedNotes.size(); i++) {
            assertEquals(expectedNotes.get(i).toString(), loadedNotes.get(i).toString());
        }
    }
}
