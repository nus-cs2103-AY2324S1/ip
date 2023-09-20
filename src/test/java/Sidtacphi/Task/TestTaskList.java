package Sidtacphi.Task;

import org.junit.jupiter.api.Test;

import Sidtacphi.Exception.SidException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTaskList {
    @Test
    public void addTaskTest() {
        try {
            TaskList taskList1 = new TaskList();
            taskList1.addTask(TaskType.DEADLINE, "deadline return book /by 2023-06-01");
            
            TaskList taskList2 = new TaskList();
            taskList2.addTask(TaskType.DEADLINE, "deadline return book /by 2023-06-01");
            assertEquals(taskList1, taskList2);
        } catch (SidException e) {
            System.out.println(e);
            System.out.println("Improperly written addTaskTest");
            assertEquals(0, 1);
        }
    }

    @Test
    public void deleteTaskTest() {
        try {
            TaskList taskList1 = new TaskList();
            taskList1.addTask(TaskType.DEADLINE, "deadline return book /by 2023-06-01");
            taskList1.deleteTask("delete 1");
            
            TaskList taskList2 = new TaskList();
            taskList2.addTask(TaskType.DEADLINE, "deadline return book /by 2023-06-01");
            taskList2.deleteTask("delete 1");
            assertEquals(taskList1, taskList2);
        } catch (SidException e) {
            System.out.println(e);
            System.out.println("Improperly written deleteTaskTest");
            assertEquals(0, 1);
        }
    }
}