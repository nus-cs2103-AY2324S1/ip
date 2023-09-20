package Sidtacphi.Task;

import org.junit.jupiter.api.Test;

import Sidtacphi.Exception.SidException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTaskList {
    @Test
    public void addTaskTest() throws SidException {
        TaskList taskList1 = new TaskList();
        taskList1.addTask(TaskType.DEADLINE, "deadline return book /by 2023-06-01");
        
        TaskList taskList2 = new TaskList();
        taskList2.addTask(TaskType.DEADLINE, "deadline return book /by 2023-06-01");
        assertEquals(taskList1, taskList2);
    }

    @Test
    public void deleteTaskTest() throws SidException {
        TaskList taskList1 = new TaskList();
        taskList1.addTask(TaskType.DEADLINE, "deadline return book /by 2023-06-01");
        taskList1.deleteTask("delete 1");
        
        TaskList taskList2 = new TaskList();
        taskList2.addTask(TaskType.DEADLINE, "deadline return book /by 2023-06-01");
        taskList2.deleteTask("delete 1");
        assertEquals(taskList1, taskList2);
    }
}