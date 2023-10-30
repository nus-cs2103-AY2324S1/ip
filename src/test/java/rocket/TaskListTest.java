package rocket;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void toString_todoDeadlineEvent_success(){
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("[T][ ] borrow book");
        stringList.add("[D][ ] return book (by: Oct 15 2023, 7:45 PM)");
        stringList.add("[E][ ] project meeting (from: Oct 16 2023, 2:00 PM to: Oct 16 2023, 4:00 PM)");
        TaskList tasks;
        try {
            tasks = new TaskList(stringList);
        } catch (RocketIllegalArgumentException e) {
            throw new RuntimeException(e);
        }
        assertEquals("[T][ ] borrow book\n" +
                "[D][ ] return book (by: Oct 15 2023, 7:45 PM)\n" +
                "[E][ ] project meeting (from: Oct 16 2023, 2:00 PM to: Oct 16 2023, 4:00 PM)\n",
                String.valueOf(tasks));

    }
}
