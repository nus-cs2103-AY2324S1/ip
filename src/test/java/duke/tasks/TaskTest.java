package duke.tasks;

import duke.Ui;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    public class stubUi extends Ui {
        public String line = "--------------------------------------------------------------------";

        public void showMarkTask(boolean isMarked, Task task) {
            if (!isMarked) {
                System.out.println("ଘ(੭ˊᵕˋ)੭ Yay! This task is completed:\n" + task.toString());
                System.out.println(line);
            } else {
                System.out.println("┐(´～｀)┌ This task is already marked as completed!");
                System.out.println(line);
            }
        }

        public void showUnmarkTask(boolean isMarked, Task task) {
            if (isMarked) {
                System.out.println("໒( ̿･ ᴥ ̿･ )ʋ All righty, I've marked this task as uncompleted:\n" + task.toString());
                System.out.println(line);
            } else {
                System.out.println("┐(´～｀)┌ This task is already marked as uncompleted!");
                System.out.println(line);
            }
        }
    }

    //this is the concrete implementation of Task, used for testing
    public class concreteTask extends Task {
        public concreteTask(int status, String task) {
            super(status, task);
        }

        @Override
        public String convertTask() {
            return "task converted";
        }
    }

    @Test
    public void testMarkSuccess() {
        //whatever is printed to the console is written to outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        stubUi ui = new stubUi();
        concreteTask task = new concreteTask(0, "Set up unit tests");
        task.mark(ui);
        String expected = "ଘ(੭ˊᵕˋ)੭ Yay! This task is completed:\n" + task + "\n" + ui.line + "\n";
        assertEquals(expected, outContent.toString());
        assertEquals(1, task.getStatus());
    }

    @Test
    public void testMarkNotSuccessful() {
        //whatever is printed to the console is written to outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        stubUi ui = new stubUi();
        concreteTask task = new concreteTask(1, "Set up unit tests");
        task.mark(ui);
        String expected = "┐(´～｀)┌ This task is already marked as completed!\n" + ui.line + "\n";
        assertEquals(expected, outContent.toString());
        assertEquals(1, task.getStatus());
    }

    @Test
    public void testUnmarkSuccessful() {
        //whatever is printed to the console is written to outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        stubUi ui = new stubUi();
        concreteTask task = new concreteTask(1, "Set up unit tests");
        task.unmark(ui);
        String expected = "໒( ̿･ ᴥ ̿･ )ʋ All righty, I've marked this task as uncompleted:\n" +
                task + "\n" + ui.line + "\n";
        assertEquals(expected, outContent.toString());
        assertEquals(0, task.getStatus());
    }

    @Test
    public void testUnmarkNotSuccessful() {
        //whatever is printed to the console is written to outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        stubUi ui = new stubUi();
        concreteTask task = new concreteTask(0, "Set up unit tests");
        task.unmark(ui);
        String expected = "┐(´～｀)┌ This task is already marked as uncompleted!\n" + ui.line + "\n";
        assertEquals(expected, outContent.toString());
        assertEquals(0, task.getStatus());
    }

    @Test
    public void testGetStatus1() {
        concreteTask task = new concreteTask(0, "Set up unit tests");
        assertEquals(0, task.getStatus());
    }

    @Test
    public void testGetStatus2() {
        concreteTask task = new concreteTask(1, "Set up unit tests");
        assertEquals(1, task.getStatus());
    }

    @Test
    public void testTask1() {
        concreteTask task = new concreteTask(1, "Set up unit tests");
        assertEquals("Set up unit tests", task.getTask());
    }

    @Test
    public void testToString1() {
        concreteTask task = new concreteTask(0, "Set up unit tests");
        assertEquals("[ ] " + "Set up unit tests", task.toString());
    }

    @Test
    public void testToString2() {
        concreteTask task = new concreteTask(1, "Set up unit tests");
        assertEquals("[X] " + "Set up unit tests", task.toString());
    }
    @Test
    public void testToString3() {
        concreteTask task = new concreteTask(3, "Set up unit tests");
        assertEquals("[X] " + "Set up unit tests", task.toString());
    }
}
