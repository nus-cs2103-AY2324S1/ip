package ui;

import duke.DukeException.DukeException;
import duke.Storage.Storage;
import duke.Task.ToDos;
import duke.Ui.Ui;
import duke.TaskList.TaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class uiTest {

    private Ui ui = new Ui();
    @Test
    public void printChat_checkMeow() {
        assertEquals("Hi Meow", ui.printChat("Hi"));
    }

    @Test
    public void hello_checkIntro() {
        assertEquals("Hello! I'm Chrainx\n" + "What can I do for you?", ui.hello());
    }

    @Test
    public void addTask_checkAddStatement() {
        assertEquals(
                "Got it, I have added this task:\n"
                + "[T][ ] sleep" + "\n"
                + "Now you have 1"
                + " tasks in the list",
                ui.addTask(new ToDos("sleep"), 1)
        );
    }

    @Test
    public void deleteTask_checkDeleteStatement() {
        assertEquals(
                "Noted, I have removed this task:\n"
                        + "[T][ ] sleep "+ "\n"
                        + "Now you have 1"
                        + " tasks in the list",
                ui.deleteTask(new ToDos("sleep"), 1)
        );
    }

    @Test
    public void bye_checkByeStatement() {
        try {
            assertEquals(
                    "Bye. Hope to see you again soon!\n" + "Wish You a wonderful day",
                    ui.bye(new Storage(), new TaskList())
            );
        } catch (DukeException e) {
            fail();
        }
    }
}

