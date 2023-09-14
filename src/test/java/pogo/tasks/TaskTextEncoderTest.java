package pogo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TaskTextEncoderTest {

    @Test
    void visitDeadline_validDeadline_encodesCorrectly() throws Exception {
        TaskTextEncoder encoder = new TaskTextEncoder();

        Deadline deadline = new Deadline("Finish report", LocalDateTime.parse("2023-09-01T18:00"));
        encoder.visit(deadline);

        String expected = "D | 0 | Finish report | 01/09/2023 18:00" + System.lineSeparator();
        assertEquals(expected, encoder.encode(new ArrayList<>()));
    }

    @Test
    void visitDeadline_taskDoneDeadline_encodesCorrectly() throws Exception {
        TaskTextEncoder encoder = new TaskTextEncoder();

        Deadline deadline = new Deadline("Finish report", LocalDateTime.parse("2023-09-01T18:00"));
        deadline.markAsDone();
        encoder.visit(deadline);

        String expected = "D | 1 | Finish report | 01/09/2023 18:00" + System.lineSeparator();
        assertEquals(expected, encoder.encode(new ArrayList<>()));
    }

    @Test
    void visitEvent_validEvent_encodesCorrectly() throws Exception {
        LocalDateTime from = LocalDateTime.parse("2023-09-01T20:00");
        LocalDateTime to = LocalDateTime.parse("2023-09-01T23:00");
        Event event = new Event("Party", from, to);
        TaskTextEncoder encoder = new TaskTextEncoder();
        encoder.visit(event);

        String expected = "E | 0 | Party | 01/09/2023 20:00 | 01/09/2023 23:00" + System.lineSeparator();
        assertEquals(expected, encoder.encode(new ArrayList<>()));
    }

    @Test
    void visitToDo_validToDo_encodesCorrectly() throws Exception {
        ToDo toDo = new ToDo("Buy groceries");
        TaskTextEncoder encoder = new TaskTextEncoder();
        encoder.visit(toDo);

        String expected = "T | 0 | Buy groceries" + System.lineSeparator();
        assertEquals(expected, encoder.encode(new ArrayList<>()));
    }

    @Test
    void decode_validTasksString_decodesCorrectly() {
        List<String> tasksString = new ArrayList<>();
        tasksString.add("D | 0 | Assignment | 01/09/2023 23:59");
        tasksString.add("T | 1 | Read book");
        tasksString.add("E | 0 | Party | 01/09/2023 20:00 | 01/09/2023 23:00");

        TaskTextEncoder encoder = new TaskTextEncoder();
        List<Task> decodedTasks = encoder.decode(tasksString);

        assertEquals(3, decodedTasks.size());

        assertTrue(decodedTasks.get(0) instanceof Deadline);
        assertFalse(decodedTasks.get(0).isDone());

        assertTrue(decodedTasks.get(1) instanceof ToDo);
        assertTrue(decodedTasks.get(1).isDone());

        assertTrue(decodedTasks.get(2) instanceof Event);
        assertFalse(decodedTasks.get(2).isDone());
    }
}
