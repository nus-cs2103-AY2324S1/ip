package seedu;  //same package as the class being tested

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTaskTest(){
        Task newTask = new Task("todo cs2103t", "todo");
        List<Task> lists = new ArrayList<>();
        lists.add(newTask);
        TaskList t = new TaskList(lists);
        assertEquals("todo cs2103t", t.get(0).description);
    }

    @Test
    public void removeTaskTest(){
        Task newTask = new Task("todo cs2103t", "todo");
        List<Task> lists = new ArrayList<>();
        for(int i = 0; i < 10; i++) lists.add(newTask);
        TaskList t = new TaskList(lists);
        try {
            t.remove(4);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(9, t.getLen());
    }
}