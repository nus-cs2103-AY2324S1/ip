package tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ToDoTest {

    @Test
    public void dataToTask_completeDescription_success() {
        String todo = "todo water plants";
        String deadline = "deadline cs2103 assignment /by 2023-09-12 14:00";
        String event = "event Jackie's birthday /from 2023-09-10 20:00 /to 2023-09-11 02:00";
        LocalDateTime deadlineTime = LocalDateTime.parse("2023-09-12 14:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime eventStartTime = LocalDateTime.parse("2023-09-10 20:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime eventEndTime = LocalDateTime.parse("2023-09-11 02:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        try {
            ToDo todoTask = new ToDo("water plants");
            Deadline deadlineTask = new Deadline("cs2103 assignment", deadlineTime);
            Event eventTask = new Event("Jackie's birthday", eventStartTime, eventEndTime);
            assertEquals(todoTask.getTaskAsString(),"[T][ ] water plants");
            assertEquals(deadlineTask.getTaskAsString(), "[D][ ] cs2103 assignment (by: Sep 12 2023 14:00)");
            assertEquals(eventTask.getTaskAsString(), "[E][ ] Jackie's birthday (from: Sep 10 2023 20:00 to: Sep 11 2023 02:00)");
        } catch (Exception e) {
            fail();
        }
    }

}
