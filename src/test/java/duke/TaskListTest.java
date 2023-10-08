package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.components.TaskList;
import duke.task.ToDos;

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
        ToDos toDos = new ToDos("item 1", false);
        TaskList taskList = new TaskList();
        taskList.addToList(toDos);
        taskList.markItem(1);
        assertTrue(taskList.getTask().get(0).isDone());
    }

    @Test
    public void markItem_massMark_success() {
        ToDos toDos1 = new ToDos("item 1");
        ToDos toDos2 = new ToDos("item 2");
        TaskList taskList = new TaskList();
        taskList.addToList(toDos1);
        taskList.addToList(toDos2);
        taskList.markItem(1, 2);
        assertTrue(taskList.getTask().get(0).isDone());
        assertTrue(taskList.getTask().get(1).isDone());
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
        assertFalse(taskList.getTask().get(0).isDone());
    }

    @Test
    public void unMarkItem_massUnMark_success() {
        ToDos toDos1 = new ToDos("item 1", true);
        ToDos toDos2 = new ToDos("item 1", true);
        TaskList taskList = new TaskList();
        taskList.addToList(toDos1);
        taskList.addToList(toDos2);
        taskList.unMarkItem(1, 2);
        assertFalse(taskList.getTask().get(0).isDone());
        assertFalse(taskList.getTask().get(1).isDone());
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
