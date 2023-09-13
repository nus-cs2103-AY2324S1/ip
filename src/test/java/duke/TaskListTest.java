package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.components.Status;
import duke.exceptions.InvalidStartEndException;
import duke.stubs.StorageStub;
import duke.stubs.TaskListStub;
import duke.stubs.UiStub;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class TaskListTest {
    private ArrayList<Task> tasks = new ArrayList<>();

    @BeforeEach
    public void loadTasks() {
        try {
            tasks.add(new ToDo(Status.NOT_DONE, "cookies"));
            tasks.add(new ToDo(Status.NOT_DONE, "bake cookies"));
            tasks.add(new ToDo(Status.NOT_DONE, "eat cookies"));
            tasks.add(new Deadline(Status.NOT_DONE, "bake more cookies", LocalDateTime.now()));
            tasks.add(new Deadline(Status.NOT_DONE, "watch lecture", LocalDateTime.now()));
            tasks.add(new Deadline(Status.NOT_DONE, "lecture quiz", LocalDateTime.now()));
            tasks.add(new Event(Status.NOT_DONE, "cookie marathon", LocalDateTime.now(),
                    LocalDateTime.now().plusHours(10)));
            tasks.add(new Event(Status.NOT_DONE, "cookie baking class", LocalDateTime.now(),
                    LocalDateTime.now().plusHours(10)));
        } catch (InvalidStartEndException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void findMatches_emptyString() {
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt");
        TaskListStub list = new TaskListStub(this.tasks, storage, ui);

        String result = list.listTasks(list.findMatches(""));
        ArrayList<Task> expectedList = tasks;
        String expected = list.listTasks(expectedList);

        assertEquals(expected, result);
    }

    @Test
    public void findMatches_keywordFound() {
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt");
        TaskListStub list = new TaskListStub(this.tasks, storage, ui);

        String result = list.listTasks(list.findMatches("cookies"));
        ArrayList<Task> expectedList = new ArrayList<>();
        expectedList.add(tasks.get(0));
        expectedList.add(tasks.get(1));
        expectedList.add(tasks.get(2));
        expectedList.add(tasks.get(3));

        String expected = list.listTasks(expectedList);

        assertEquals(expected, result);
    }

    @Test
    public void findMatches_keywordNotFound() {
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt");
        TaskListStub list = new TaskListStub(this.tasks, storage, ui);

        String result = list.listTasks(list.findMatches("math"));
        ArrayList<Task> expectedList = new ArrayList<>();
        String expected = list.listTasks(expectedList);

        assertEquals(expected, result);
    }

    @Test
    public void findMatches_keywordWithSpaces() {
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt");
        TaskListStub list = new TaskListStub(this.tasks, storage, ui);

        String result = list.listTasks(list.findMatches("cookies   "));
        ArrayList<Task> expectedList = new ArrayList<>();
        String expected = list.listTasks(expectedList);

        assertEquals(expected, result);
    }

    @Test
    public void findMatches_moreThanOneKeyword() {
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt");
        TaskListStub list = new TaskListStub(this.tasks, storage, ui);

        String result = list.listTasks(list.findMatches("bake cookies"));
        ArrayList<Task> expectedList = new ArrayList<>();
        expectedList.add(tasks.get(1));
        String expected = list.listTasks(expectedList);

        assertEquals(expected, result);
    }

    @Test
    public void findMatches_byDate() {
        UiStub ui = new UiStub();
        StorageStub storage = new StorageStub("./src/test/testdata.txt");
        TaskListStub list = new TaskListStub(this.tasks, storage, ui);

        String result = list.listTasks(list.findMatches(LocalDateTime.now().toString()));
        ArrayList<Task> expectedList = new ArrayList<>();

        String expected = list.listTasks(expectedList);

        assertEquals(expected, result);
    }
}
