package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void addToList_addOne_success() {
        ToDos toDos = new ToDos("item 1");
        TaskList taskList = new TaskList();
        taskList.addToList(toDos);
        int initialSize = taskList.getSize();
        ToDos toDos2 = new ToDos("item 2");
        taskList.addToList(toDos2);
        assertEquals(initialSize + 1, 2);
    }

    @Test
    public void deleteFromList_deleteOne_success() {
        ToDos toDos = new ToDos("item 1");
        TaskList taskList = new TaskList();
        taskList.addToList(toDos);
        int initialSize = taskList.getSize();
        taskList.deleteFromList(1);
        assertEquals(initialSize - 1, 0);
    }

    @Test
    public void deleteFromList_noSuchItem_success() {
        ToDos toDos = new ToDos("item 1");
        TaskList taskList = new TaskList();
        taskList.addToList(toDos);
        int initialSize = taskList.getSize();
        taskList.deleteFromList(3);
        assertEquals(initialSize, 1);
    }

    @Test
    public void markItem_markOne_success() {
        ToDos toDos = new ToDos("item 1");
        TaskList taskList = new TaskList();
        taskList.addToList(toDos);
        taskList.markItem(1);
        assertEquals(taskList.getTask().get(0).toString(), "[T] [ ] item 1");
    }

    @Test
    public void markItem_noSuchItem_success() {
        ToDos toDos = new ToDos("item 1");
        TaskList taskList = new TaskList();
        taskList.addToList(toDos);
        taskList.markItem(3);
        assertEquals(taskList.getTask().get(0).toString(), "[T] [ ] item 1");
    }

    @Test
    public void unMarkItem_unMarkOne_success() {
        ToDos toDos = new ToDos("item 1", true);
        TaskList taskList = new TaskList();
        taskList.addToList(toDos);
        taskList.unMarkItem(1);
        assertEquals(taskList.getTask().get(0).toString(), "[T] [X] item 1");
    }

    @Test
    public void toString_list_success() {
        ToDos toDos = new ToDos("item 1");
        TaskList taskList = new TaskList();
        taskList.addToList(toDos);
        assertEquals("------------------------------------" + "\n"
                + "1. [T] [ ] item 1" + "\n\n"
                + "------------------------------------",
                taskList.toString());
    }
}