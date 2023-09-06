package toolpackage;

import dukepackage.DukeException;

import taskpackage.Deadlines;
import taskpackage.Events;
import taskpackage.ToDos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.Charset;

public class TaskListTest {

    @Test
    public void toggleDone_success() {
        try {
            Ui ui = new Ui();

            TaskList tasks = new TaskList();
            tasks.addItem(new ToDos("Todo 1", "0"), ui);
            tasks.addItem(new Deadlines("Deadline 1", "by 2023-08-31", "0"), ui);
            tasks.addItem(new Events("Event 1", "from 2023-08-30", "to 2023-08-31", "0"), ui);

            assertEquals(String.format("Nice! I've marked this task as done:%n [T][X] Todo 1%n"),
                    tasks.toggleDone("1", "mark", ui));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void toggleDone_incorrectIndex_showErrorMsg() {
        try {
            Ui ui = new Ui();

            TaskList tasks = new TaskList();
            tasks.addItem(new ToDos("Todo 1", "0"), ui);
            tasks.addItem(new Deadlines("Deadline 1", "by 2023-08-31", "0"), ui);
            tasks.addItem(new Events("Event 1", "from 2023-08-30", "to 2023-08-31", "0"), ui);

            
            byte[] emojiByteCode = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0xB1};
            String emoji = new String(emojiByteCode, Charset.forName("UTF-8"));
            assertEquals(String.format(emoji + " OOPS!!! Please indicate an appropriate index within the list range."),
                    tasks.toggleDone("5", "unmark", ui));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void removeItem_success() {
        try {
            Ui ui = new Ui();

            TaskList tasks = new TaskList();
            tasks.addItem(new ToDos("Todo 1", "0"), ui);
            tasks.addItem(new Deadlines("Deadline 1", "by 2023-08-31", "0"), ui);
            tasks.addItem(new Events("Event 1", "from 2023-08-30", "to 2023-08-31", "0"), ui);

            assertEquals(String.format(
                            "Noted. I've removed this task:%n [T][] Todo 1%n" +
                            "Now you have 2 tasks in the list.%n"),
                    tasks.removeItem("1", ui));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void removeItem_incorrectIndex_showErrorMsg() {
        try {
            Ui ui = new Ui();

            TaskList tasks = new TaskList();
            tasks.addItem(new ToDos("Todo 1", "0"), ui);
            tasks.addItem(new Deadlines("Deadline 1", "by 2023-08-31", "0"), ui);
            tasks.addItem(new Events("Event 1", "from 2023-08-30", "to 2023-08-31", "0"), ui);

            byte[] emojiByteCode = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0xB1};
            String emoji = new String(emojiByteCode, Charset.forName("UTF-8"));
            assertEquals(String.format(emoji + " OOPS!!! Please indicate an appropriate index within the list range."),
                    tasks.removeItem("0", ui));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
