package Storage;

import Parser.DateTime;
import TaskManager.Deadlines;
import TaskManager.Events;
import TaskManager.Tasks;
import TaskManager.ToDos;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void testTaskList() {

        ArrayList<Tasks> taskList = new ArrayList<>();
        ArrayList<Tasks> taskList1 = new ArrayList<>();
        ArrayList<Tasks> taskList2 = new ArrayList<>();
        ArrayList<Tasks> taskList3 = new ArrayList<>();
        ArrayList<Tasks> taskList4 = new ArrayList<>();
        ArrayList<Tasks> taskList5 = new ArrayList<>();

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

        ToDos newtodo = new ToDos("TestTodo");
        ToDos newtodo1 = new ToDos("TestTodo1");

        Deadlines newdeadline = new Deadlines("TestDeadline", formattedDate);
        Deadlines newdeadline1 = new Deadlines("TestDeadline", formattedDate1);

        Events newevent = new Events("TestEvent", formattedDate, formattedDate);
        Events newevent1 = new Events("TestEvent", formattedDate, formattedDate1);


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