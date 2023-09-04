package storage;

import parser.DateTime;
import taskmanager.Deadline;
import taskmanager.Event;
import taskmanager.Task;
import taskmanager.ToDo;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void testTaskList() {

        ArrayList<Task> taskList = new ArrayList<>();
        ArrayList<Task> taskList1 = new ArrayList<>();
        ArrayList<Task> taskList2 = new ArrayList<>();
        ArrayList<Task> taskList3 = new ArrayList<>();
        ArrayList<Task> taskList4 = new ArrayList<>();
        ArrayList<Task> taskList5 = new ArrayList<>();

        TaskList tasks1 = new TaskList(taskList);
        TaskList tasks2 = new TaskList(taskList1);
        TaskList tasks3 = new TaskList(taskList2);
        TaskList tasks4 = new TaskList(taskList3);
        TaskList tasks5 = new TaskList(taskList4);
        TaskList tasks6 = new TaskList(taskList5);

        DateTime dateTime = new DateTime();
        String formattedDate = dateTime.formatDateTime("23/08/2023 1800");
        String formattedDate1 = dateTime.formatDateTime("23/08/2023 1830");
        String formattedDate2 = dateTime.formatDateTime("23/08/2024 1800");

        ToDo newtodo = new ToDo("TestTodo");
        ToDo newtodo1 = new ToDo("TestTodo1");

        Deadline newdeadline = new Deadline("TestDeadline", formattedDate);
        Deadline newdeadline1 = new Deadline("TestDeadline", formattedDate1);

        Event newevent = new Event("TestEvent", formattedDate, formattedDate);
        Event newevent1 = new Event("TestEvent", formattedDate, formattedDate1);


        //add tasks to tasks1
        tasks1.add(newtodo);
        tasks1.add(newdeadline);
        tasks1.add(newevent);

        //add tasks to tasks2
        tasks2.add(newtodo1);
        tasks2.add(newdeadline);
        tasks2.add(newevent);

        //add tasks to tasks3
        tasks3.add(newtodo);
        tasks3.add(newdeadline1);
        tasks3.add(newevent);

        //add tasks to tasks4
        tasks4.add(newtodo);
        tasks4.add(newdeadline);
        tasks4.add(newevent1);

        //add tasks to tasks5
        tasks5.add(newtodo);
        tasks5.add(newdeadline);
        tasks5.add(newevent);

        //leave tasks6 empty

        assertTrue(tasks1.taskListEqual(tasks1));
        assertFalse(tasks1.taskListEqual(tasks2));
        assertFalse(tasks1.taskListEqual(tasks3));
        assertFalse(tasks1.taskListEqual(tasks4));
        assertTrue(tasks1.taskListEqual(tasks5));
        assertFalse(tasks1.taskListEqual(tasks6));

    }
}