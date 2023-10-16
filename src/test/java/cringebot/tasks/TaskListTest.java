package cringebot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import cringebot.exceptions.CringeBotException;
import cringebot.parser.Parser;

public class TaskListTest {
    @Test
    public void initialiseTest1() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.size());
    }

    @Test
    public void initialiseTest2() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        assertEquals(0, tasks.size());
    }

    @Test
    public void addTaskTest1() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        try {
            tasks.addItem(Parser.TaskType.TODO, "todo return books");
        } catch (CringeBotException e) {
            System.out.println("something went wrong");
        }
        assertEquals(1, tasks.size());
        assertEquals("[T][ ] return books", tasks.getTaskWithIndex(0).toString());
    }

    @Test
    public void addTaskTestRecurring1() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        try {
            tasks.addRecurringItems(
                    Parser.TaskType.DEADLINE,
                    "deadline project meeting /by 2023-09-18 /recurring 2023-09-28"
            );
        } catch (CringeBotException e) {
            System.out.println("something went wrong");
        }
        assertEquals(2, tasks.size());
        assertEquals("[D][ ] project meeting (by: Sep 18 2023)", tasks.getTaskWithIndex(0).toString());
        assertEquals("[D][ ] project meeting (by: Sep 25 2023)", tasks.getTaskWithIndex(1).toString());
    }

    @Test
    public void addTaskTestRecurring2() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        String errorMessage = "";
        try {
            tasks.addRecurringItems(
                    Parser.TaskType.DEADLINE,
                    "deadline project meeting /by 2023-09-18 /recurring 2023"
            );
        } catch (CringeBotException e) {
            errorMessage = e.getMessage();
        }
        assertEquals(0, tasks.size());
        assertEquals(errorMessage, "OOPS!!! Please a date with the format yyyy-mm-dd. :((");
    }

    @Test
    public void removeTaskTest1() {
        TaskList tasks = new TaskList(new ArrayList<Task>());
        try {
            tasks.addItem(Parser.TaskType.TODO, "todo return books");
        } catch (CringeBotException e) {
            System.out.println("something went wrong");
        }
        assertEquals(1, tasks.size());
        assertEquals("[T][ ] return books", tasks.getTaskWithIndex(0).toString());

        try {
            tasks.deleteItem("delete 1");
        } catch (CringeBotException e) {
            System.out.println("something went wrong");
        }
        assertEquals(0, tasks.size());
    }
}
