package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class TaskParserTest {

    @Test
    public void todoSaveString() {
        Todo td = new Todo("Test", true);
        String parseOutput = TaskParser.generateSaveString(new Task[]{td});
        String expectedStr = "T || Test || 1";
        assertEquals(expectedStr, parseOutput);
    }

    @Test
    public void deadlineSaveString() {
        LocalDateTime date = LocalDateTime.of(2023, 8, 19, 10, 00);
        Deadline ddl = new Deadline("Test", date);
        String parseOutput = TaskParser.generateSaveString(new Task[]{ddl});
        String expectedStr = "D || Test || 0 || 19/8/2023 1000";
        assertEquals(expectedStr, parseOutput);
    }

    @Test
    public void eventSaveString() {
        LocalDateTime startDate = LocalDateTime.of(2023, 8, 19, 10, 00);
        LocalDateTime endDate = LocalDateTime.of(2023, 8, 20, 22, 30);
        Event evt = new Event("Test", true, startDate, endDate);
        String parseOutput = TaskParser.generateSaveString(new Task[]{evt});
        String expectedStr = "E || Test || 1 || 19/8/2023 1000 to 20/8/2023 2230";
        assertEquals(expectedStr, parseOutput);
    }

    @Test
    public void todoSaveParse() {
        String saveString = "T || test || 1";
        Optional<Task> taskOptional = TaskParser.parseSave(saveString);
        assertEquals(true, taskOptional.isPresent());
        assertEquals(true, taskOptional.get() instanceof Todo);
        assertEquals("test", taskOptional.get().getName());
    }

    @Test
    public void deadlineSaveParse() {
        String saveString = "D || test2 || 0 || 19/9/2023 1800";
        Optional<Task> taskOptional = TaskParser.parseSave(saveString);
        assertEquals(true, taskOptional.isPresent());
        assertEquals(true, taskOptional.get() instanceof Deadline);
        assertEquals("test2", taskOptional.get().getName());
    }

    @Test
    public void eventSaveParse() {
        String saveString = "E || test3 || 0 || 19/8/2023 1000 to 21/8/2023 2000";
        Optional<Task> taskOptional = TaskParser.parseSave(saveString);
        assertEquals(true, taskOptional.isPresent());
        assertEquals(true, taskOptional.get() instanceof Event);
        assertEquals("test3", taskOptional.get().getName());
    }

}
