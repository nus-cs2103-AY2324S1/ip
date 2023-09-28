package bruno;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bruno.task.Deadline;
import bruno.task.Task;
import bruno.task.ToDo;

public class UiTest {

    private UI ui = new UI();
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test void testDisplayGreeting() {
        String s = "Woof Woof! I'm Bruno\n" + "How can I help you?";
        assertEquals(s, ui.displayGreeting());
    }

    @Test void testDisplayBye() {
        assertEquals("Bye Bye! Hope to see you again soon!", ui.displayBye());
    }

    @Test void testDisplayAddMessage() {
        TaskList taskList = new TaskList(new Storage("xxx", "yyy"), ui);
        List<Task> tasks = taskList.getList();
        tasks.add(new ToDo("laundry", ""));
        taskList.setList(tasks);
        String res = ui.displayAddMessage(taskList);
        assertEquals("Woof. I have added this task:\n" + "[T][ ] laundry", res);
    }

    @Test void testDisplayMarkMessage() {
        TaskList taskList = new TaskList(new Storage("xxx", "yyy"), ui);
        List<Task> tasks = taskList.getList();
        tasks.add(new ToDo("laundry", ""));
        taskList.setList(tasks);
        String res = ui.displayMarkMessage(taskList, 1);
        assertEquals("Woof Woof! I have marked the task as done.\n" + "[T][ ] laundry", res);
    }

    @Test void testDisplayUnMarkMessage() {
        TaskList taskList = new TaskList(new Storage("xxx", "yyy"), ui);
        List<Task> tasks = taskList.getList();
        tasks.add(new Deadline("ip", "2023-09-22 23:59", ""));
        taskList.setList(tasks);
        String res = ui.displayUnmarkMessage(taskList, 1);
        assertEquals("OK, I have marked the task as not done yet.\n"
                + "[D][ ] ip (by: 22 September 2023 23:59)", res);
    }

    @Test void testDisplayDeleteMessage() {
        String taskString = "[T][ ] laundry";
        String res = ui.displayDeleteMessage(taskString);
        assertEquals("I have removed this task from your tasks:\n[T][ ] laundry", res);
    }

    @Test void testDisplaySchedule() {
        String taskInfo = "1. [E][ ] hackathon (from: 29 September 2023 18:00 to: 01 October 2023 10:00";
        String res = ui.displaySchedule(taskInfo, 1);
        assertEquals("Here is the schedule for the given date:\n1. [E][ ] hackathon (from: 29 September "
                + "2023 18:00 to: 01 October 2023 10:00", res);
    }

    @Test void testDisplaySearch() {
        String taskInfo = "";
        int counter = 0;
        String res = ui.displaySearch(taskInfo, counter);
        assertEquals("There are no items matching your search.", res);
    }

    @Test void testDisplayNoteMessage() {
        TaskList taskList = new TaskList(new Storage("xxx", "yyy"), ui);
        List<Task> tasks = taskList.getList();
        tasks.add(new Deadline("ip", "2023-09-22 23:59", ""));
        taskList.setList(tasks);
        String res = ui.displayNoteMessage(taskList, 1);
        assertEquals("I have added note to the task:\n[D][ ] ip (by: 22 September 2023 23:59)", res);
    }
}
