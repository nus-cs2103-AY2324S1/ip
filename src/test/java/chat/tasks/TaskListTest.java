package chat.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import chat.exceptions.ChatException;

public class TaskListTest {

    @Test
    public void testDelete() {
        TaskList tasklist = new TaskList();
        tasklist.add("hi", true);
        try {
            tasklist.delete(0);
        } catch (ChatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertEquals(tasklist.getSize(), 0);
    }

    @Test
    public void testSize() {
        TaskList tasklist = new TaskList();
        tasklist.add("hi", true);
        assertEquals(tasklist.getSize(), 1);
    }

    @Test
    public void testString() {
        TaskList tasklist = new TaskList();
        tasklist.add("hi", true);
        assertEquals(tasklist.toString(), "1.[T][X] hi\nYou have 1 task(s) in the list.");
    }

    @Test
    public void testDone() {
        TaskList tasklist = new TaskList();
        tasklist.add("hi", true);
        try {
            assertEquals(tasklist.setDone(true, 0), "This task is already marked as done!\n[T][X] hi");
        } catch (ChatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testFind() {
        TaskList tasklist = new TaskList();
        tasklist.add("h", true);
        tasklist.add("hi", true);
        tasklist.add("hii", true);
                try {
            assertEquals(tasklist.find("hi"), "[T][X] hi\n[T][X] hii\nThere were 2 tasks containing hi.");
        } catch (ChatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testSortName() {
        TaskList tasklist = new TaskList();
        tasklist.add("cii", true);
        tasklist.add("bi", true);
        tasklist.add("a", true);
        try {
            assertEquals(tasklist.sortName(), "1.[T][X] a\n2.[T][X] bi\n3.[T][X] cii\nYou have 3 task(s) in the list.\nTasks have been sorted by name.");
        } catch (ChatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testSortDate() {
        TaskList tasklist = new TaskList();
        tasklist.add("cii", true, LocalDate.of(2012,2,2), null);
        tasklist.add("bi", true, LocalDate.of(2023,2,2), LocalTime.of(16,0));
        tasklist.add("a", true);
        try {
            assertEquals(tasklist.sortDate(), "1.[T][X] a\n2.[D][X] cii (by: 2012-02-02 )\n3.[D][X] bi (by: 2023-02-02 16:00)\nYou have 3 task(s) in the list.\nTasks have been sorted by date.");
        } catch (ChatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testSortType() {
        TaskList tasklist = new TaskList();
        tasklist.add("cii", true, LocalDate.of(2012,2,2), null, LocalDate.of(2013,1,1), null);
        tasklist.add("bi", true, LocalDate.of(2023,2,2), LocalTime.of(16,0));
        tasklist.add("a", true);
        try {
            assertEquals(tasklist.sortType(), "1.[T][X] a\n2.[D][X] bi (by: 2023-02-02 16:00)\n3.[E][X] cii (from: 2012-02-02  to: 2013-01-01 )\nYou have 3 task(s) in the list.\nTasks have been sorted by type.");
        } catch (ChatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
