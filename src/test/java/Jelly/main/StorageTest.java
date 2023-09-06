package Jelly.main;

import Jelly.exceptions.JellyException;
import Jelly.task.Deadline;
import Jelly.task.Task;
import Jelly.task.Todo;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StorageTest {
    @Test
    public void startUpTest() throws IOException, JellyException {
        File tempTasks = File.createTempFile("tempTasks",".txt");

        String filePath = tempTasks.getAbsolutePath();
        Storage storage = new Storage(filePath);

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new Todo("Do that"));
        taskList.get(0).markAsDone();
        taskList.add(new Deadline("Do this thing", "18/12/2020 1900"));

        TaskList savedList = new TaskList(taskList);
        storage.saveAndExit(savedList);

        ArrayList<Task> loadedList = storage.startUp();
        tempTasks.deleteOnExit();

        for (int i = 0; i < taskList.size(); i++) {
            assertEquals(taskList.get(i).getDescription(),
                    loadedList.get(i).getDescription());
            assertEquals(taskList.get(i).getIsDone(),
                    loadedList.get(i).getIsDone());
        }
    }

}
